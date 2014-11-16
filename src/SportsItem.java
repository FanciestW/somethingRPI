public class SportsItem extends RandomizedObject
{
	private static final String[] items = {
		"football",
		"baseball",
		"bat",
		"puck",
		"racquet",
		"mitt",
		"basketball"
	};
	
	private static final String[] descriptions = {
		"An American football.",
		"A rawhide leather baseball.",
		"A Louisville Slugger baseball bat.",
		"A hockey puck.",
		"A newly strung tennis racquet.",
		"A baseball mitt.",
		"An orange basketball."
	};
	
	public SportsItem()
	{
		super(items, descriptions);
	}
}
