import java.util.ArrayList;

public class Binder<BinderItem> {
	
	private class Entry {
		String name;
		BinderItem item;
		
		public Entry(){
			
		}
		public Entry(String name, BinderItem item) {
			super();
			this.name = name;
			this.item = item;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BinderItem getItem() {
			return item;
		}
		public void setItem(BinderItem item) {
			this.item = item;
		}
		@Override
		public String toString(){
			return item.toString();
		}
	}
	
	ArrayList<Entry> binder;
	
	public Binder(){
		binder = new ArrayList<Entry>();
	}
	
	public Binder(ArrayList<Entry> binder) {
		this.binder = binder;
	}
	
	public void addEntry(String name, BinderItem item){
		binder.add(new Entry(name, item));
	}
	
	public BinderItem getItem(int index){
		
		return (BinderItem) Utilities.copy(binder.get(index).getItem());
	}
	
	public BinderItem getItem(String name){
		for (Entry entry: binder){
			if(name.equals(entry.getName())){
				return (BinderItem) Utilities.copy(entry.getItem());
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		String s = "";
		int i = 1;
		for (Entry entry: binder){
			s += String.valueOf(i) + ". " + entry.toString() + "\n";
			i++;
		}
		return s;
	}
	
}
