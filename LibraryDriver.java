
public class LibraryDriver {
	public static void main(String[] args)
	{
		LibraryView lv = new LibraryView();
		LibraryModel lm = new LibraryModel();
		LibraryController lc = new LibraryController(lv, lm);
	}
}
