package dimensionizer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Dimension 
{
	private String dimensionName_;
	private String units_;
	private double dimension_;
	private TreeMap<String, Dimension> nestedDimensions_;

	public Dimension( String dimensionName, String units, double dimension ) 
	{
		dimensionName_ = dimensionName;
		units_ = units;
		dimension_ = dimension;
		nestedDimensions_ = new TreeMap<String, Dimension>();
	}

	public Dimension( String dimensionName, double dimension ) 
	{
		this( dimensionName, "cm", dimension );
	}

	public Dimension() 
	{
		this( "", "cm", 0.0 );
	}

	public String getDimensionName() 
	{
		return dimensionName_;
	}

	public void setDimensionName( String dimensionName ) 
	{
		this.dimensionName_ = dimensionName;
	}

	public String getUnits() 
	{
		return units_;
	}

	public void setUnits( String units ) 
	{
		this.units_ = units;
	}

	public double getDimension() 
	{
		return dimension_;
	}

	public void setDimension( double dimension ) 
	{
		this.dimension_ = dimension;
	}

	public void resize( double resizeFactor )
	{
		dimension_ *= resizeFactor;
	}

	public Collection<String> getNestedDimensionNames() 
	{
		return nestedDimensions_.keySet();
	}

	public void setNestedDimension(List<Dimension> dimensionList ) 
	{
		for( Iterator<Dimension> iter = dimensionList.iterator(); iter.hasNext(); )
		{
			Dimension entry = iter.next();
			nestedDimensions_.put( entry.getDimensionName(), entry );
		}
	}

	public Dimension getNestedDimension( String name )
	{
		return nestedDimensions_.get( name );
	}

	public String toString()
	{
		return toString( 1.0 );
	}

	public String toString( double resizeFactor )
	{
		return toString( resizeFactor, "" );
	}
	
	public String toString( double resizeFactor, String offset )
	{
		StringBuffer strBuf = new StringBuffer( "" );

		if( nestedDimensions_.isEmpty() )
		{
			strBuf.append( offset + dimensionName_ + " = " + dimension_ );	

			if( resizeFactor != 1.0 )
			{
				strBuf.append( ", resized[" + resizeFactor + "] = " + (dimension_*resizeFactor) );
			}

			strBuf.append( " " + units_ + "\n" );
		}
		else
		{
			strBuf.append( offset + "Dimension Group [" + dimensionName_ + "]\n" );

			Collection<String> coll = getNestedDimensionNames();
			for( Iterator<String> iter= coll.iterator(); iter.hasNext(); )
			{
				Dimension dimension = nestedDimensions_.get( iter.next() );
				strBuf.append( dimension.toString( resizeFactor, offset+"\t" ) );
			}
		}

		
		return strBuf.toString();
	}
}
