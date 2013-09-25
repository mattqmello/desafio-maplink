package view;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import model.GeoCoordinate;
import model.util.JsonSpecialConverter;
import model.util.RouteInfoSpecialConverter;
import br.com.maplink2.webservices.Point;
import br.com.maplink2.webservices.RouteDetails;
import br.com.maplink2.webservices.RouteInfo;
import br.com.maplink2.webservices.RouteLine;
import br.com.maplink2.webservices.RouteOptions;
import br.com.maplink2.webservices.RouteSoapProxy;
import br.com.maplink2.webservices.RouteStop;
import br.com.maplink2.webservices.Vehicle;
import dao.FileManager;

/**
 * Console application for access the MapLink's WebServices 
 * to calculate routing based on a initial input file. 
 * For use only with MapLink's chalenge TOKEN.
 * @author Matheus Q. de Mello
 * @since Terça 24/9/2013
 */
public class ConsoleView {
	
	private static final String FILE_LOCATION = "src/Arquivo_entrada_MapLink.json";
	private static final String TOKEN_MAPLINK = "c13iyCvmcC9mzwkLd0LCbmYC5mUF5m2jNGNtNGt6NmK6NJK=";
	
	public static void main(String[] args) {
		
		List<GeoCoordinate> listaGeoCoordnts = null;
		
		// LER arquivo de texto com JsonArray de objetos Json 	//
		// e converter em uma lista de objetos GeoCoordinate	//
		try {
			listaGeoCoordnts = 
					JsonSpecialConverter.toGeoCoordinates(
							FileManager.readFile(FILE_LOCATION).toString());
		} catch (Exception e) {
			System.err.println("Erro ao ler/converter codigo Json:");
			e.printStackTrace();
			System.exit(1);
		}
		
		// ITERAR GEO-COORDINATES PARA REQUISICAO AO WEB SERVICE	//
		Point[] points;
		RouteLine routLine;
		RouteStop routStOrig;
		RouteStop routStDest;
		RouteOptions routOpt;
		List<RouteInfo> listaRouteInfos = new ArrayList<>();
		
		int i=1;
		for (GeoCoordinate geoCoordinate : listaGeoCoordnts) {
			System.out.printf("\nRequisitando WebService com GeoCoordinate Nro.%d..." , i);
			
			// Instanciar objeto PROXY para acesso aos metodos de Web Service da MapLink
			RouteSoapProxy routeSoapProxy = new RouteSoapProxy();
			
			// Instanciar objetos 'RouteStop', 'RouteOptions' e 'Point's// 
			// (os points armazenados em um 'vetor')					//
			points = new Point[]{
					new Point(
						geoCoordinate.getOriginCoordinate()[GeoCoordinate.LONGITUDE],
						geoCoordinate.getOriginCoordinate()[GeoCoordinate.LATITUDE]),
					new Point(
						geoCoordinate.getDestinationCoordinate()[GeoCoordinate.LONGITUDE],
						geoCoordinate.getDestinationCoordinate()[GeoCoordinate.LATITUDE])
				};
			
			routStOrig = new RouteStop("Route stop de origem", points[0]);
			routStDest = new RouteStop("Route stop de destino", points[1]);
			
			routLine= new RouteLine(2, "72,118,255", 1);
			
			routOpt = new RouteOptions(
					"portugues", 
					new RouteDetails(0, 0, true, null, null, null), 
					new Vehicle(55, 0.126f, 2.390, 110, 2),
					new RouteLine[]{routLine});
			
			try {
				listaRouteInfos.add(
						routeSoapProxy.getRoute(
								new RouteStop[]{routStOrig, routStDest}, routOpt, TOKEN_MAPLINK));
				System.out.println("Sucesso!");
			} catch (RemoteException e) {
				System.out.printf("Erro ao requisitar geracao de rota #%d:\n", i);
				e.printStackTrace();
				System.exit(1);
			}
			i++;
		}
		
		// ITERAR E CONVERTER ROTAS OBTIDAS PARA JSONS OBJECTS - PRIOR(alta)//
		JSONArray jsonArray = RouteInfoSpecialConverter.toJsonArray(listaRouteInfos);
		
		// CONVERTER EM UM ARQUIVO DE TEXTO O JSONArray de ROUTEINFOs - PRIOR(alta)//
		FileManager.writeFile(jsonArray.toString());
		
	}

}
