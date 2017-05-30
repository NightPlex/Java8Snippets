import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * NightPlex please.
 */
public class LambdaSnippet {

    private static List<Person> persons = new LinkedList<>();

    public class Person {
        private String name;
        private String address;
        private int age;

        public Person(String name, String address, int age) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    public boolean addPersonToList(Person person) {
        if (person != null) {
            persons.add(person);
            return true;
        }
        return false;
    }

    public void populateList() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name: ");
            String name = s.nextLine();
            System.out.println("Enter address: ");
            String address = s.nextLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(s.nextLine());
            if (age == 0) break;
            LambdaSnippet l = new LambdaSnippet();
            l.addPersonToList(new Person(name, address, age));
        }
    }

    /**
     * Example usange of map in streams
     */
    public List<Person> streamMapExample() {
        //Replaces all spaces in name with "_"
        return persons.stream().map(person -> {
                    person.setName(person.getName().replaceAll(" ", "_"));
                    return person;
                }).collect(Collectors.toList());
    }

    public List<Person> streamFilterExample() {
        //Removes all persons that are younger than 18
        return persons.stream()
                .filter(person -> person.age > 18)
                .collect(Collectors.toList());
    }

    public static void main(String args[]) {
        LambdaSnippet snippet = new LambdaSnippet();
        snippet.populateList();
        //Old way to do it.
        for(Person s : snippet.streamFilterExample()) {
            System.out.println("Good old for each: " + s.getName());
        }
        //Java 8 gives easier way to for each a list.
        snippet.streamFilterExample()
                .forEach(person -> System.out.println("Java8 filter example output: " + person.getName()));
        //Now lets move to changing the values inside the list.
        snippet.streamMapExample().forEach(person -> System.out.println("Java8 map example output: " + person.getName()));
    }
}

