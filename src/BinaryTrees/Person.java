package BinaryTrees;

public class Person implements Comparable<Person> {
	private int age;
	public Person(int age) {
		this.age=age;
	}
	public int getAge() {
		return 0;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int compareTo(Person e) {
		return age - e.age;
	}
	@Override
	public String toString() {
		return "age=" + age;
	}
	
}
