
public class Book extends RandomizedObject
{
	private static final String[] titles = {
			"Harry Potter",
			"Lord of the Rings",
			"A Tale of Two Cities",
			"The Odyssey",
			"Walden",
			"To Kill A Mockingbird",
			"1984",
			"One Flew Over The Cuckoo's Nest",
			"The Great Gatsby"
	};
	private static final String[] descriptions = {
			"A fantasy book in which a boy discovers adventure at a magical school named Hogwarts.",
			"An epic trilogy in which two hobbits named Frodo and Sam adventure with " +
					"a fellowship of men, elves, dwarves, and wizards to destroy the evil "
					+ "Sauron's ring of power.",
			"A Dickens novel focused on London and Paris during the period of the French Revolution.",
			"An epic poem by Homer in which Odysseus undergoes many obstacles to return from " +
					"war to his home in Ithaca.",
			"Henry David Thoreau's account of living simply in accordance with nature.",
			"A classic novel about race, prejudice, and childhood in which a man Atticus " +
					"struggles against the unfair charge of rape for Tom Robinson.",
			"A dystopian novel warning against the power of totalitarian government.",
			"A psychological novel about a series of unusual events in a sanitarium.",
			"An account of high-class living in New York in the 1920s and its pitfalls."
	};
	
	public Book()
	{
		super(titles, descriptions);
		name = "a copy of " + titles;
	}
}
