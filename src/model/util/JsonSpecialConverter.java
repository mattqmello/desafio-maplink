package model.util;

import java.util.ArrayList;
import java.util.List;

import model.GeoCoordinate;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Special Json Converter converts a json code from a source file 
 * to a GeoCoordinate object as specified.
 * @author Matheus Q. de Mello
 * @since Terça 24/9/2013
 */
public class JsonSpecialConverter {
	
	/**
	 * Converts JSONArray, specified by this project context, to GeoCoordinates list,
	 * specifically from an Json Source String due the requirements of this project.
	 * @param json_src String containing a Json source code.
	 * @return A List of GeoCoordinates
	 */
	public static List<GeoCoordinate> toGeoCoordinates(String json_src) throws Exception{
		List<GeoCoordinate> listaGeoCoordnts = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(json_src);
		JSONObject jsonObj;
		
		for (int i=0; i<jsonArray.length(); i++) {
			jsonObj = (JSONObject) jsonArray.get(i);
			listaGeoCoordnts.add(
				new GeoCoordinate(
					jsonObj.getInt("id"),
					new double[]{
						jsonObj.getJSONObject("originCoordinate").getDouble("latitude"),
						jsonObj.getJSONObject("originCoordinate").getDouble("longitude")
					},
					new double[]{
						jsonObj.getJSONObject("destinationCoordinate").getDouble("latitude"),
						jsonObj.getJSONObject("destinationCoordinate").getDouble("longitude")
					}
				)
			);
		}
		return listaGeoCoordnts;
	}

}
