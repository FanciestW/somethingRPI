public class Food extends RandomizedObject
{
	private static final String[] types = {
		"pizza",
		"yogurt",
		"soda",
		"water",
		"cheeseburger",
		"chips"
	};
	
	private static final String[] descriptions = {
		"Good old-fashioned cheese pizza.",
		"Vanilla yogurt.",
		"You can't tell what brand it is, but it's some type of non-diet soda.",
		"Ordinary water.",
		"A burger with American cheese.",
		"Crispy potato chips."
	};
	
	public Food()
	{
		super(types, descriptions);
	}
}
