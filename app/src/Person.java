
import java.util.*;

public class Person implements Comparable<Person> {

 /*** create a person giving him a name with default
 * sex set to OTHER */
 public Person(String name) {
	this(name, Sex.OTHER);
 }
 public Person(String name, Sex sex) {
	this.name = name; this.sex = sex;
 }
 
 public void setSex(Sex sex){
	this.sex = sex;
 }
 
 public Sex getSex(){return sex;}

 public void setName(String name) {
	this.name = name;
 }

 public String getName() {
	return name;
 }

 public String getFullName() {
	return name + " " + getParentName();
 }

 public String getReversedFullName() {
	return getParentName() + " " + name;
 }

 public String getParentName() {
	if (parent != null)return parent.name;
	else return "";
 }

 public Person setParent(Person person) {
	if (this.equals(person)) {
	 return person;
	} else
	if (isParent(person)) {
	 person.setChild(this);
	 return person;
	} else if (hasParent()) {
	 return parent;
	} else {
	 parent = person;
	 person.setChild(this);
	 return parent;
	}
 }

 public Person getParent() {
	return parent;
 }

 public Person setChild(Person person) {
	if (this.equals(person)) {
	 return person;
	} else
	if (person.isParent(this)) {
	 if (!isChild(person)) {
		children.add(person);
	 }
	 return person.parent;
	} else if (person.hasParent()) {
	 return parent;
	} else {
	 person.parent = this;
	 children.add(person);
	 return parent;
	}
 }

 public Person getChild(String name) {
	for (int i = 0; i < children.size(); i++) {
	 Person p = children.get(i);
	 if (p.name.equalsIgnoreCase(name)) {
		return p;
	 }
	}
	return null;
 }

 public boolean isParent(Person person) {
	if (parent != null) {
	 return person.name.equalsIgnoreCase(parent.name);
	}
	return false;
 }

 public boolean isChild(Person person) {

	return getChild(person.name) != null;
 }

 public boolean isRelative(Person person) {
	if (parent == null || person.parent == null) {
	 return false;
	}
	return person.parent.name.equalsIgnoreCase(parent.name);
 }

 public Person removeParent(Person person) {
	if (isParent(person)) {
	 person.children.remove(this);
	 parent = null;
	 return person;
	}
	return null;
 }

 public Person removeChild(Person person) {
	if (isChild(person)) {
	 children.remove(person);
	 person.parent = null;
	 return person;
	}
	return null;
 }

 public int getChildrenCount() {
	return children.size();
 }

 public Person[] getChildren() {
	return (Person[])children.toArray();
 }

 public boolean hasParent() {
	return parent != null;
 }

 @Override
 public boolean equals(Object o) {
	Person p = (Person)o;
	if (parent == null && p.parent == null) {
	 return p.name.equalsIgnoreCase(name) &&
	 p.getSex() == getSex();
	} else
	if (parent == null || p.parent == null) {
	 return false;
	} else {
	 return (p.name.equalsIgnoreCase(name) &&
		p.getSex() == getSex() &&
		p.parent.name.equalsIgnoreCase(parent.name));
	}
 }

 @Override
 public int compareTo(Person p1) {
	return getFullName().compareToIgnoreCase(p1.getFullName());
 }

 @Override
 public String toString() {
	// TODO: Implement this method
	
	return "[Name: " + name + ", Parent name: "
	+ getParentName() + ", Sex: " + sex + "]";
 }
 
 
 
 // inner enum class
enum Sex{FEMALE, MALE, OTHER;}

 // instance variables
 private Sex sex = Sex.OTHER;
 private String name = "";
 private Person parent = null;
 private Vector<Person> children = new Vector<Person>();

}
