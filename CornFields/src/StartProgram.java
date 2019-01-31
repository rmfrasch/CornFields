
//Imports, which includes the other classes of fieldDescription and ListFieldHelper
import java.util.List;
import java.util.Scanner;

import controller.ListFieldHelper;
import model.FieldDescription;

/**
 * @author RYANF This program is intended to be what the users sees when they
 *         are working in the database. This provides a menu that gives the user
 *         options to add an item, remove an item, alter an items, and view the
 *         list of fields.
 */
public class StartProgram {

	static Scanner in = new Scanner(System.in);
	// creates the ListFieldHelper object
	static ListFieldHelper lfh = new ListFieldHelper();

	/**
	 * This method is intended to add a new field to the database. Prompts user for
	 * field name, pass number, range number.
	 */
	private static void addAnItem() {
		FieldDescription toAdd = new FieldDescription();
		System.out.print("Enter a field Name: ");
		String fieldName = in.nextLine();
		toAdd.setFieldName(fieldName);
		System.out.print("Enter number of passes: ");
		int passNum = in.nextInt();
		toAdd.setPassNum(passNum);
		System.out.print("Enter number of ranges: ");
		int rangeNum = in.nextInt();
		toAdd.setRangeNum(rangeNum);
		lfh.insertField(toAdd);

	}

	/**
	 * This method is intended to delete a record from the SQL database.
	 */
	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the name of the field to delete: ");
		String name = in.nextLine();
		FieldDescription toDelete = new FieldDescription(name);
		lfh.deleteItem(toDelete);

	}

	/**
	 * This method is intended to prompt the user to edit an item in the database.
	 * Prompts user for the field name to be altered, and then asks what part of the
	 * field will need to be altered.
	 */
	private static void editAnItem() {

		// defines the list 'foundItems'
		List<FieldDescription> foundItems = null;

		System.out.print("Enter the Field Name: ");
		String fieldName = in.nextLine();
		foundItems = lfh.searchForItemByFieldName(fieldName);

		// If the list is NOT empty, then it displays results, using a for loop to
		// output information to the use.
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (FieldDescription l : foundItems) {
				System.out.println(l.print());
			}
			//Prompts user which ID they would like to alter.
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			//Prompts user which variable to alter.
			FieldDescription toEdit = lfh.searchForItemByFieldID(idToEdit);
			System.out.println("Retrieved " + toEdit.getFieldID() + " from " + toEdit.getFieldName());
			System.out.println("1 : Update Field Name");
			System.out.println("2 : Update Pass Number");
			System.out.println("3 : Update Range Number");
			int update = in.nextInt();
			in.nextLine();
			
			//Takes the user input and allow them to change the variable.
			if (update == 1) {
				System.out.print("New Field Name: ");
				String newStore = in.nextLine();
				toEdit.setFieldName(newStore);
			} else if (update == 2) {
				System.out.print("New Total Passes: ");
				int newItem1 = in.nextInt();
				toEdit.setPassNum(newItem1);
			} else if (update == 3) {
				System.out.print("New Total Ranges");
				int newItem2 = in.nextInt();
				toEdit.setRangeNum(newItem2);
			}
			//updates database and commits changes
			lfh.updateField(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	//Runs the menu for the user.
	public static void main(String[] args) {
		runMenu();

	}
	//Runs the menu for the user, prompting them with options for selection.
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Corn Field Database");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			//Checks user input, and selects the method the user selected.
			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				//To close connections to the database and close the program.
				lfh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	/**
	 *To view all records in the selected table of the database. 
	 */
	private static void viewTheList() {
		List<FieldDescription> allItems = lfh.showAllItems();
		for (FieldDescription singleItem : allItems) {
			System.out.println(singleItem.print());
		}

	}

}
