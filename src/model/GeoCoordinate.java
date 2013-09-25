package model;

/**
 * GeoCoordinate represents a geographic coordinate from a Json object
 * according to MapLink's chalenge input file.
 * @author Matheus Q. de Mello
 * @since Terça 24/9/2013
 */
public class GeoCoordinate {

	public static final int LATITUDE = 0;
	public static final int LONGITUDE = 1;

	private long id;
	private double[] originCoordinate;
	private double[] destinationCoordinate;
	
	// CONSTRUTORES
	public GeoCoordinate() {
	}

	public GeoCoordinate(long id, double[] originCoordinate,
			double[] destinationCoordinate) {
		super();
		this.id = id;
		this.originCoordinate = originCoordinate;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	// GETS/SETS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the two OriginGeoCoordinates as a String separated by a space.
	 * @return The String representation of the OriginGeoCoordinates
	 */
	public String getOriginCoordinateString() {
		StringBuffer strBff = new StringBuffer();
		char space = 32;
		strBff.append(this.originCoordinate[0]);
		strBff.append(space);
		strBff.append(this.originCoordinate[1]);
		return strBff.toString();
	}

	public double[] getOriginCoordinate() {
		return originCoordinate;
	}

	public void setOriginCoordinate(double[] originCoordinate) {
		this.originCoordinate = originCoordinate;
	}
	/**
	 * Gets the two DestinationGeoCoordinates as a String separated by a space.
	 * @return The String representation of the DestinationGeoCoordinates
	 */
	public String getDestinationCoordinateString() {
		StringBuffer strBff = new StringBuffer();
		char space = 32;
		strBff.append(this.destinationCoordinate[0]);
		strBff.append(space);
		strBff.append(this.destinationCoordinate[1]);
		return strBff.toString();
	}

	public double[] getDestinationCoordinate() {
		return destinationCoordinate;
	}

	public void setDestinationCoordinate(double[] destinationCoordinate) {
		this.destinationCoordinate = destinationCoordinate;
	}
}
