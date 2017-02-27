import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;


public class ReadCSV {
	//Three Arraylist people is original list,temp is temporary,and newone is tested list;
	ArrayList<Human> people = new ArrayList<Human>();
	ArrayList<Human> newone = new ArrayList<Human>();
	ArrayList<Human> temp = new ArrayList<Human>();

	public void ReadCSV(String text,String data) throws IOException {
		String Humans = ReadFile(data);
		String lines[] = Humans.split("\n");
		File file2 = new File("output.txt");
		FileWriter fw = new FileWriter(file2.getAbsoluteFile());
		PrintWriter pw = new PrintWriter(fw);
		int num1 = 0;
		int num2 = 0;
		//adding each person to arraylist
		for (int i = 1; i < lines.length; i++) {
			String words[] = lines[i].split("\\|");
			long id = Long.parseLong(words[0]);
			long ssn = Long.parseLong(words[5]);
			Human newOne = new Human(id, words[1], words[2], words[3], words[4], ssn);
			people.add(newOne);
		}

		//original list to temp list
		for (int i = 0; i < people.size(); i++) {
			temp.add(people.get(i));
		}

		String Commands = ReadFile(text);
		String lines2[] = Commands.split("\n");
		//this for controls errors line by line
		for (int i = 0; i < lines2.length; i++) {
			if (lines2[i].contains("<") && lines2[i].contains(">")) {

				String words[] = lines2[i].split(" ");
				for (int j = 0; j < words.length; j++) {
					if (words[j].contains("<")) {

						num1 = j;
					}
					if (words[j].contains(">")) {

						num2 = j;
					}

				}

				String[] hold = words[num1].split("<");
				String[] hold2 = words[num2].split(">");
				long less = Long.parseLong(hold[1]);
				long more = Long.parseLong(hold2[1]);
//if there is no exception again this part calculates the time (millisecond) and calls print function
				if ((less < more) && (hold[0].equals(hold2[0]))) {
					pw.printf("CommandText: %s", lines2[i]);
					pw.println(System.lineSeparator());
					pw.println("Results:");
					pw.printf("Empty Rowset\n");
					pw.printf("------------------------------------------\n");
					pw.printf("Process time: 000 milliseconds\n");
					pw.println(System.lineSeparator());

				} else {
					long start = System.currentTimeMillis();
					newone = Control(people, lines2[i], pw);
					print(newone, pw, lines2[i]);
					long end = System.currentTimeMillis();
					long time = (end - start);
					pw.printf("Process time: %d milliseconds\n", time);
					pw.println(System.lineSeparator());
					//every execution the arraylist became original again with respect to temporary
					newone.clear();
					newone.addAll(temp);
				}
			} else {
				long start = System.currentTimeMillis();
				newone = Control(people, lines2[i], pw);
				print(newone, pw, lines2[i]);
				long end = System.currentTimeMillis();
				long time = (end - start);
				pw.printf("Process time: %d milliseconds\n", time);
				pw.println(System.lineSeparator());
				newone.clear();
				newone.addAll(temp);

			}

		}
		pw.close();
	}

	//print function for output
	public void print(ArrayList<Human> list, PrintWriter pw, String commands) throws IOException {
		String words[] = commands.split(" ");
		String[] First = new String[list.size()+1];
		String[] Second = new String[list.size()+1];
		String[] Third = new String[list.size()+1];
		String[] Fourth = new String[list.size()+1];
		String[] Fifth = new String[list.size()+1];
		String[] Sixth = new String[list.size()+1];
		String dir[] = words[1].split(",");

		

		if (list.size() == 0) {
			pw.printf("CommandText: %s", commands);
			pw.println(System.lineSeparator());
			pw.println("Results:");
			pw.print("Empty Rowset\n");
		} else {
			pw.printf("CommandText: %s", commands);
			pw.println(System.lineSeparator());
			pw.println("Results:");
			for (int i = 0; i < dir.length; i++) {
				for (int j = 1; j < list.size()+1; j++) {
					if (dir[i].equals("CID")) {
						if (i == 0) {
							String str = Long.toString(list.get(j-1).getId());
							First[0]="CID";
							First[j] = str;
						}
						if (i == 1) {
							String str = Long.toString(list.get(j-1).getId());
							Second[0]="CID";
							Second[j] = str;
						}
						if (i == 2) {
							String str = Long.toString(list.get(j-1).getId());
							Third[0]="CID";
							Third[j] = str;
						}
						if (i == 3) {
							String str = Long.toString(list.get(j-1).getId());
							Fourth[0]="CID";
							Fourth[j] = str;
						}
						if (i == 4) {
							String str = Long.toString(list.get(j-1).getId());
							Fifth[0]="CID";
							Fifth[j] = str;
						}
						if (i == 5) {
							String str = Long.toString(list.get(j-1).getId());
							Sixth[0]="CID";
							Sixth[j] = str;
						}
					}
					if (dir[i].equals("FirstName")) {
						if (i == 0) {
							First[0]="FirstName";
							First[j] = list.get(j-1).getName();
						}
						if (i == 1) {
							Second[0]="FirstName";
							Second[j] = list.get(j-1).getName();
						}
						if (i == 2) {
							Third[0]="FirstName";
							Third[j] = list.get(j-1).getName();
						}
						if (i == 3) {
							Fourth[0]="FirstName";
							Fourth[j] = list.get(j-1).getName();
						}
						if (i == 4) {
							Fifth[0]="FirstName";
							Fifth[j] = list.get(j-1).getName();
						}
						if (i == 5) {
							Sixth[0]="FirstName";
							Sixth[j] = list.get(j-1).getName();
						}
					}
					if (dir[i].equals("LastName")) {
						if (i == 0) {
							First[0]="LastName";
							First[j] = list.get(j-1).getLastname();
						}
						if (i == 1) {
							Second[0]="LastName";
							Second[j] = list.get(j-1).getLastname();
						}
						if (i == 2) {
							Third[0]="LastName";
							Third[j] = list.get(j-1).getLastname();
						}
						if (i == 3) {
							Fourth[0]="LastName";
							Fourth[j] = list.get(j-1).getLastname();
						}
						if (i == 4) {
							Fifth[0]="LastName";
							Fifth[j] = list.get(j-1).getLastname();
						}
						if (i == 5) {
							Sixth[0]="LastName";
							Sixth[j] = list.get(j-1).getLastname();
						}
					}
					if (dir[i].equals("City")) {
						if (i == 0) {
							First[0]="City";
							First[j] = list.get(j-1).getCity();
						}
						if (i == 1) {
							Second[0]="City";
							Second[j] = list.get(j-1).getCity();
						}
						if (i == 2) {
							Third[0]="City";
							Third[j] = list.get(j-1).getCity();
						}
						if (i == 3) {
							Fourth[0]="City";
							Fourth[j] = list.get(j-1).getCity();
						}
						if (i == 4) {
							Fifth[0]="City";
							Fifth[j] = list.get(j-1).getCity();
						}
						if (i == 5) {
							Sixth[0]="City";
							Sixth[j] = list.get(j-1).getCity();
						}
					}
					if (dir[i].equals("AddressLine1")) {
						if (i == 0) {
							First[0]="AddressLine1";
							First[j] = list.get(j-1).getAddress();
						}
						if (i == 1) {
							Second[0]="AddressLine1";
							Second[j] = list.get(j-1).getAddress();
						}
						if (i == 2) {
							Third[0]="AddressLine1";
							Third[j] = list.get(j-1).getAddress();
						}
						if (i == 3) {
							Fourth[0]="AddressLine1";
							Fourth[j] = list.get(j-1).getAddress();
						}
						if (i == 4) {
							Fifth[0]="AddressLine1";
							Fifth[j] = list.get(j-1).getAddress();
						}
						if (i == 5) {
							Sixth[0]="AddressLine1";
							Sixth[j] = list.get(j-1).getAddress();
						}
					}
					if (dir[i].equals("SocialSecurityNumber")) {
						if (i == 0) {
							First[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							First[j] = str2;
						}
						if (i == 1) {
							Second[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							Second[j] = str2;
						}
						if (i == 2) {
							Third[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							Third[j] = str2;
						}
						if (i == 3) {
							Fourth[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							Fourth[j] = str2;
						}
						if (i == 4) {
							Fifth[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							Fifth[j] = str2;
						}
						if (i == 5) {
							Sixth[0]="SocialSecurityNumber";
							String str2 = Long.toString(list.get(j-1).getSSN());
							Sixth[j] = str2;
						}
					}

				}

			}
			for (int k = 0; k < First.length; k++) {
				if (dir.length == 1) {	
						pw.printf("%-21s\n", First[k]);
				
				}
				if (dir.length == 2) {
					if(First[0].equals("AddressLine1"))
					{
						pw.printf("%-34s%-21s\n", First[k], Second[k]);
					}
					else{
						pw.printf("%-21s%-21s\n", First[k], Second[k]);
					}
					
				}
				if (dir.length == 3) {
					if(Second[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-34s%-21s\n", First[k], Second[k], Third[k]);
					}
					if(First[0].equals("AddressLine1"))
					{
						pw.printf("%-34s%-21s%-21s\n", First[k], Second[k], Third[k]);
					}
					else{
						pw.printf("%-21s%-21s%-21s\n", First[k], Second[k], Third[k]);
					}
					
				}
				if (dir.length == 4) {
					if(Second[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-34s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k]);
					}
					if(First[0].equals("AddressLine1"))
					{
						pw.printf("%-34s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k]);
					}
					if(Third[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-34s%-21s\n", First[k], Second[k], Third[k], Fourth[k]);
					}
					else{
						pw.printf("%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k]);
					}

				}
				if (dir.length == 5) {
					if(Second[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-34s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k]);
					}
					if(First[0].equals("AddressLine1"))
					{
						pw.printf("%-34s%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k]);
					}
					if(Third[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-34s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k]);
					}
					if(Fourth[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-21s%-34s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k]);
					}
					else{
						pw.printf("%-21s%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k]);
					}
					
				}
				if (dir.length == 6) {
					if(Second[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-34s%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					if(First[0].equals("AddressLine1"))
					{
						pw.printf("%-34s%-21s%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					if(Third[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-34s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					if(Fourth[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-21s%-34s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					if(Fifth[0].equals("AddressLine1"))
					{
						pw.printf("%-21s%-21s%-21s%-21s%-34s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					else{
						pw.printf("%-21s%-21s%-21s%-21s%-21s%-21s\n", First[k], Second[k], Third[k], Fourth[k], Fifth[k],Sixth[k]);
					}
					
				}

			}
			
		}

		
		pw.printf("------------------------------------------\n");
	}

	//Main Control Section
	public ArrayList<Human> Control(ArrayList<Human> list, String line, PrintWriter pw) throws IOException {
		String words[] = line.split(" ");
		ArrayList<Human> temp = new ArrayList<Human>();
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals("AND") || words[i].equals("WHERE")) {
				if (words[i + 1].contains("<")) {
					String[] com = words[i + 1].split("<");
					if (com[0].equals("CID") || com[0].equals("SocialSecurityNumber")) {

						temp = delete(list, words[i + 1], pw);
					} else {
						temp.clear();
					}
				}
				if (words[i + 1].contains(">")) {
					String[] com = words[i + 1].split(">");
					if (com[0].equals("CID") || com[0].equals("SocialSecurityNumber")) {

						temp = delete(list, words[i + 1], pw);
					} else {
						temp.clear();
					}
				}
				if (words[i + 1].contains("=")) {
					String[] com = words[i + 1].split("=");
					if (com[0].equals("CID") || com[0].equals("SocialSecurityNumber")) {

						temp = delete(list, words[i + 1], pw);
					} else {
						temp.clear();
					}
				}
				if (words[i + 1].contains("~")) {
					String[] com = words[i + 1].split("~");
					if (com[0].equals("City") || com[0].equals("AddressLine1") || com[0].equals("LastName")
							|| com[0].equals("FirstName")) {
						temp = delete(list, words[i + 1], pw);
					} else {

						temp.clear();
					}

				}
			}

		}

		return temp;
	}

	//if there is no exception then delete unnecessary persons
	public ArrayList<Human> delete(ArrayList<Human> a, String str, PrintWriter pw) {
		Comparator<Human> name = new NameComparator();
		Comparator<Human> last = new LastNameComparator();
		Comparator<Human> ssn = new SSNComparator();
		Comparator<Human> cid = new CIDComparator();
		Comparator<Human> address = new AddressComparator();
		Comparator<Human> city = new CityComparator();
		Comparator<Human> bname = new BinaryNameComparator();
		Comparator<Human> blast = new BinaryLastNameComparator();
		Comparator<Human> bcity = new BinaryCityComparator();
		Comparator<Human> baddress = new BinaryAddressComparator();

		if (str.contains("=")) {
			String[] com = str.split("=");
			if (com[0].equals("CID")) {
				sort(a, 0, a.size() - 1, cid);
				long ssnnum = Long.parseLong(com[1]);
				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, cid);
				if (strpos == -1) {
					
					a.clear();
				} else {
					
					Human temp = a.get(strpos);
					a.clear();
					a.add(temp);
				}
				return a;

			}
			if (com[0].equals("SocialSecurityNumber")) {

				sort(a, 0, a.size() - 1, ssn);
				long ssnnum = Long.parseLong(com[1]);
				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, ssn);
				if (strpos == -1) {
					
					a.clear();
				} else {
				
					Human temp = a.get(strpos);
					a.clear();
					a.add(temp);
				}
				return a;

			}

		}
		if (str.contains("~")) {
			String[] com = str.split("~");
			if (com[0].equals("FirstName")) {
				String cap = com[1].substring(0, 1).toUpperCase() + com[1].substring(1);

				sort(a, 0, a.size() - 1, name);
				int strpos = BinarySearchStr(a, cap, 0, a.size() - 1, bname, name);
				if (strpos == -1) {
					
					a.clear();
				} else {
					int UP = LineerTraversalUPName(cap, strpos);
					int DOWN = LineerTraversalDownName(cap, strpos);
					a.subList(0, UP).clear();
					a.subList(DOWN - UP + 1, a.size()).clear();
				}

				return a;
			}
			if (com[0].equals("LastName")) {
				String cap = com[1].substring(0, 1).toUpperCase() + com[1].substring(1);
				sort(a, 0, a.size() - 1, last);
				int strpos = BinarySearchStr(a, cap, 0, a.size() - 1, blast, last);
				if (strpos == -1) {
					
					a.clear();
				} else {
					int UP = LineerTraversalUPLastName(cap, strpos);
					int DOWN = LineerTraversalDownLastName(cap, strpos);
					a.subList(0, UP).clear();
					a.subList(DOWN - UP + 1, a.size()).clear();
					return a;
				}
			}
			if (com[0].equals("City")) {
				String cap = com[1].substring(0, 1).toUpperCase() + com[1].substring(1);
				sort(a, 0, a.size() - 1, city);
				int strpos = BinarySearchStr(a, cap, 0, a.size() - 1, bcity, city);
				if (strpos == -1) {
					
					a.clear();
				} else {
					int UP = LineerTraversalUPCity(cap, strpos);
					int DOWN = LineerTraversalDownCity(cap, strpos);
					a.subList(0, UP).clear();
					a.subList(DOWN - UP + 1, a.size()).clear();
					return a;
				}
			}
			if (com[0].equals("AddressLine1")) {
				String cap = com[1].substring(0, 1).toUpperCase() + com[1].substring(1);
				sort(a, 0, a.size() - 1, address);
				int strpos = BinarySearchStr(a, cap, 0, a.size() - 1, baddress, address);
				if (strpos == -1) {
					
					a.clear();
					return a;
				} else {
					int UP = LineerTraversalUPAddress(cap, strpos);
					int DOWN = LineerTraversalDownAddress(cap, strpos);
					a.subList(0, UP).clear();
					a.subList(DOWN - UP + 1, a.size()).clear();
					return a;
				}
			}
		}
		if (str.contains(">")) {
			String[] com = str.split(">");
			if (com[0].equals("CID")) {

				sortCID(a);
				long ssnnum = Long.parseLong(com[1]);
				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, cid);
				if (strpos == -1) {
					while (strpos == -1) {
						strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, cid);
						ssnnum++;
					}
					a.subList(0, strpos).clear();
				} else {
					a.subList(0, strpos + 1).clear();
				}
				return a;

			}
			if (com[0].equals("SocialSecurityNumber")) {

				sortSSN(a);
				long ssnnum = Long.parseLong(com[1]);
				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, ssn);
				if (strpos == -1) {
					while (strpos == -1) {
						strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, ssn);
						ssnnum++;
					}
					a.subList(0, strpos).clear();
				} else {
					a.subList(0, strpos + 1).clear();
				}
				return a;
			}
		}
		if (str.contains("<")) {
			String[] com = str.split("<");
			if (com[0].equals("CID")) {

				long ssnnum = Long.parseLong(com[1]);
				sortCID(a);
				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, cid);

				if (strpos == -1) {
					while (strpos == -1) {
						strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, cid);
						ssnnum--;

					}
					a.subList(strpos + 1, a.size()).clear();
				} else {
					a.subList(strpos, a.size()).clear();
				}
				return a;

			}
			if (com[0].equals("SocialSecurityNumber")) {

				sortSSN(a);
				long ssnnum = Long.parseLong(com[1]);

				int strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, ssn);

				if (strpos == -1) {
					while (strpos == -1) {
						strpos = BinarySearchInt(a, ssnnum, 0, a.size() - 1, ssn);
						ssnnum--;
					}
					a.subList(strpos + 1, a.size()).clear();
				} else {
					a.subList(strpos, a.size()).clear();
				}
				return a;
			}

		}
		return a;

	}

	//LineerTraversal funcs after binary search
	public int LineerTraversalDownName(String str, int pos) {

		while (people.get(pos).getName().startsWith(str) && pos != people.size() - 1) {
			pos++;
		}
		if (pos == people.size() - 1)
			return pos;
		else {
			return pos - 1;
		}
	}

	public int LineerTraversalUPName(String str, int pos) {
		while (people.get(pos).getName().startsWith(str) && pos != 0) {
			pos--;
		}
		if (pos == 0)
			return pos;
		else {
			return pos + 1;
		}
	}

	public int LineerTraversalDownLastName(String str, int pos) {
		while (people.get(pos).getLastname().startsWith(str) && pos != people.size() - 1) {
			pos++;
		}
		if (pos == people.size() - 1)
			return pos;
		else {
			return pos - 1;
		}
	}

	public int LineerTraversalUPLastName(String str, int pos) {
		while (people.get(pos).getLastname().startsWith(str) && pos != 0) {
			pos--;
		}
		if (pos == 0)
			return pos;
		else {
			return pos + 1;
		}
	}

	public int LineerTraversalDownCity(String str, int pos) {
		while (people.get(pos).getCity().startsWith(str) && pos != people.size() - 1) {
			pos++;
		}
		if (pos == people.size() - 1)
			return pos;
		else {
			return pos - 1;
		}
	}

	public int LineerTraversalUPCity(String str, int pos) {
		while (people.get(pos).getCity().startsWith(str) && pos != 0) {
			pos--;
		}
		if (pos == 0)
			return pos;
		else {
			return pos + 1;
		}
	}

	public int LineerTraversalDownAddress(String str, int pos) {
		while (people.get(pos).getAddress().startsWith(str) && pos != people.size() - 1) {
			pos++;
		}
		if (pos == people.size() - 1)
			return pos;
		else {
			return pos - 1;
		}
	}

	public int LineerTraversalUPAddress(String str, int pos) {
		while (people.get(pos).getAddress().startsWith(str) && pos != 0) {
			pos--;
		}
		if (pos == 0)
			return pos;
		else {
			return pos + 1;
		}
	}

	//Quick Sort Method
	int partition(ArrayList<Human> a, int low, int high, Comparator<Human> comp) {
		Human pivot = a.get(high);
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			if (comp.compare(a.get(j), pivot) < 0 || comp.compare(a.get(j), pivot) == 0) {
				i++;
				Human temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
			}
		}
		Human temp2 = a.get(i + 1);
		a.set(i + 1, pivot);
		a.set(high, temp2);
		return i + 1;
	}

	public void sort(ArrayList<Human> a, int low, int high, Comparator<Human> comp) {
		if (a.isEmpty() || a.size() == 0) {
			return;
		}

		if (low < high) {
			int pi = partition(a, low, high, comp);
			sort(a, low, pi - 1, comp);
			sort(a, pi + 1, high, comp);
		}
	}

	public void sortSSN(ArrayList<Human> a) {

		int length = a.size();
		quickSortSSN(a, 0, length - 1);
	}
	public void sortCID(ArrayList<Human> a) {

		int length = a.size();
		quickSortCID(a, 0, length - 1);
	}

	private void quickSortSSN(ArrayList<Human> a, int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		
		Human pivot = a.get(lowerIndex + (higherIndex - lowerIndex) / 2);
		
		while (i <= j) {
			while (a.get(i).getSSN() < pivot.getSSN()) {
				i++;
			}
			while (a.get(j).getSSN() > pivot.getSSN()) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(a, i, j);
				
				i++;
				j--;
			}
		}
		if (lowerIndex < j)
			quickSortSSN(a, lowerIndex, j);
		if (i < higherIndex)
			quickSortSSN(a, i, higherIndex);
	}

	private void quickSortCID(ArrayList<Human> a, int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		
		Human pivot = a.get(lowerIndex + (higherIndex - lowerIndex) / 2);
		
		while (i <= j) {
			while (a.get(i).getId() < pivot.getId()) {
				i++;
			}
			while (a.get(j).getId() > pivot.getId()) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(a, i, j);
				
				i++;
				j--;
			}
		}
		if (lowerIndex < j)
			quickSortCID(a, lowerIndex, j);
		if (i < higherIndex)
			quickSortCID(a, i, higherIndex);
	}
	
	private void exchangeNumbers(ArrayList<Human> a, int i, int j) {
		Human temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
	}

	
	//Binary Search for longs and integers
	public int BinarySearchInt(ArrayList<Human> a, long value, int left, int right, Comparator<Human> comp) {
		while (left <= right) {
			double Dmid = Math.floor((right - left) / 2) + left;
			int mid = (int) Dmid;
			Human test = new Human(value, null, null, null, null, value);
			Human mid1 = a.get(mid);
			if (comp.compare(mid1, test) == 0) {
				return mid;
			}
			if (comp.compare(test, mid1) < 0) {
				right = mid;
				right = right - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
	//Binary Search for Strings
	public int BinarySearchStr(ArrayList<Human> a, String str, int left, int right, Comparator<Human> comp,
			Comparator<Human> comp2) {
		while (left <= right) {
			double Dmid = Math.floor((right - left) / 2) + left;
			int mid = (int) Dmid;
			Human test = new Human(0, str, str, str, str, 0);
			Human mid1 = a.get(mid);
			if (comp.compare(mid1, test) == 0) {
				return mid;
			}
			if (comp2.compare(test, mid1) < 0) {
				right = mid;
				right = right - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	//this section reads the input file
	public String ReadFile(String STR) throws IOException {
		File file = new File(STR);
		FileReader fileReader = new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}
		String text = stringBuffer.toString();
		return text;
	}
//Comparators for binary and quick sort
	static class BinaryNameComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			int a = o1.getName().length();
			int b = o2.getName().length();
			int count = 0;
			char[] first = o1.getName().toCharArray();
			char[] second = o2.getName().toCharArray();
			for (int i = 0; i < Math.min(a, b); i++) {
				if (first[i] == second[i]) {
					count++;
				}
			}
			if (count == a) {
				return 0;
			} else if (count == b) {
				return 0;
			}
			return -1;
		}
	}

	static class BinaryLastNameComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			int a = o1.getLastname().length();
			int b = o2.getLastname().length();
			int count = 0;
			char[] first = o1.getLastname().toCharArray();
			char[] second = o2.getLastname().toCharArray();
			for (int i = 0; i < Math.min(a, b); i++) {
				if (first[i] == second[i]) {
					count++;
				}
			}
			if (count == a) {
				return 0;
			} else if (count == b) {
				return 0;
			}
			return -1;
		}
	}

	static class BinaryCityComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			int a = o1.getCity().length();
			int b = o2.getCity().length();
			int count = 0;
			char[] first = o1.getCity().toCharArray();
			char[] second = o2.getCity().toCharArray();
			for (int i = 0; i < Math.min(a, b); i++) {
				if (first[i] == second[i]) {
					count++;
				}
			}
			if (count == a) {
				return 0;
			} else if (count == b) {
				return 0;
			}
			return -1;
		}
	}

	static class BinaryAddressComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			int a = o1.getAddress().length();
			int b = o2.getAddress().length();
			int count = 0;
			char[] first = o1.getAddress().toCharArray();
			char[] second = o2.getAddress().toCharArray();
			for (int i = 0; i < Math.min(a, b); i++) {
				if (first[i] == second[i]) {
					count++;
				}
			}
			if (count == a) {
				return 0;
			} else if (count == b) {
				return 0;
			}
			return -1;
		}
	}

	//Compare methods
	static class NameComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

	static class LastNameComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return o1.getLastname().compareTo(o2.getLastname());
		}
	}

	static class SSNComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return (int) (o1.getSSN() - o2.getSSN());
		}
	}

	static class CIDComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return (int) (o1.getId() - o2.getId());
		}
	}

	static class CityComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return o1.getCity().compareTo(o2.getCity());
		}
	}

	static class AddressComparator implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return o1.getAddress().compareTo(o2.getAddress());
		}
	}
}
