# Collections API Lab Discussion
### Jake Heller (jeh113), Andy He (ash98), Zack Schrage (zjs5), Shaan Gondalia (sg491)
### TEAM 03


## In your experience using these collections, are they hard or easy to use?

* We think they're pretty easy to use in general.
* Sometimes there's some confusion between methods available in ArrayLists and Collections. Normally this isn't an issue because the IDE recognizes that the method does not exist in such cases.


## In your experience using these collections, do you feel mistakes are easy to avoid?

* In general, "mistakes" are easy enough to avoid. That's because the method names are clear and their behavior is intuitive.

## What methods are common to all collections (except Maps)?

* add()
* clear()
* contains()
* remove()

* Maps are excluded because they have behavior that is very different from other classes.


## What methods are common to all Deques?
* addLast()
* offerLast()
* peekFirst()
* pollFirst()
* All of the methods that are available in collections.


## What is the purpose of each interface implemented by LinkedList?

* Deque - access to both sides of list
* List - Access specific elements by index, adding/removing elements
* Cloneable - Allows the list to be cloned
* Serializable - allows the data of the list to be serialized


## How many different implementations are there for a Set?

* There are 9 different implementations for a Set. This justifies it being an interface, as many classes rely on its API.


## What is the purpose of each superclass of PriorityQueue?

* AbstractQueue - Provides skeletal implementation of queue operations
* AbstractCollection - Provides skeletal implementation of collection interface, minimizing effort required to implement the Collections interface
* Object - Every java class is a subclass of the Object class, which defines basic behavior such as hashing and equality.


## What is the purpose of the collection utility classes?

* This class consists exclusively of static methods that operate on or return collections. For instance, Collections.sort() sorts a list.

## API Characterics applied to Collections API

* Easy to learn

* Provides for extension

* Leads to readable code

* Hard to misuse