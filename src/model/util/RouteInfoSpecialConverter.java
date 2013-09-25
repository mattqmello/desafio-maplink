package model.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.maplink2.webservices.RouteInfo;

/**
 * This Special Json Converter converts a List of RouteInfos to a Json Array
 * @author Matheus Q. de Mello
 * @since Terça 24/9/2013
 */
public class RouteInfoSpecialConverter {
	
	/**
	 * Converts a List of RouteInfos to a Json Array.
	 * @param listaRouteInfos
	 * @return a Json Array containing a set of RouteInfos.
	 */
	public static JSONArray toJsonArray(List<RouteInfo> listaRouteInfos) {
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		
		for (RouteInfo routeInfo : listaRouteInfos) {
			jsonObject = new JSONObject();
			
			jsonObject.put("id", routeInfo.getRouteId());
			jsonObject.put("fuelCost", routeInfo.getRouteTotals().getTotalfuelCost());
			jsonObject.put("distance", routeInfo.getRouteTotals().getTotalDistance());
			jsonObject.put("totalTime", routeInfo.getRouteTotals().getTotalTime());
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray;
	}

}
