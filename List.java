public interface List<T>{


	public void add(T d);

	public void add(int index, T d);

	public T get(int index);

	public T delete(int index);

	public int size();

	public boolean isEmpty();

	public String toString();

}