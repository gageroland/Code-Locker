/*
 * Robert Roland
 * STL Vector
 */

#include <iostream>
#include <algorithm>
using namespace std;
#pragma once

template <typename Object>
class Vector
{
public:
	explicit Vector(int initSize = 0) : theSize{ initSize }, theCapacity{ initSize + SPARE_CAPACITY }
	{
		#if DEBUG
		cout << "Vector Constuctor" << endl;
		#endif

		objects = new Object[theCapacity];

		/*
		 * This constructor allows the user to initialize the Vector class with a size (default 0) and
		 * a capacity larger than the size to allow push_backs without having to change the capacity.
		 * This "spare capacity" can be changed on line 392.
		 */
	}

	Vector(const Vector & rhs) : theSize{ rhs.theSize }, theCapacity{ rhs.theCapacity }, objects{ nullptr }
	{
		#if DEBUG
		cout << "Copy Constructor" << endl;
		#endif

		objects = new Object[theCapacity];
		for (int k = 0; k < theSize; ++k)
		{
			objects[k] = rhs.objects[k];
		}

		/*
		 * This copy constructor creates a new vector that swaps a copy from the old vector to a new one.
		 */
	}

	Vector & operator = (const Vector & rhs)
	{
		#if DEBUG
		cout << "Copy Assignment" << endl;
		#endif

		Vector copy = rhs;
		std::swap(*this, copy);
		return *this;

		/*
		 * The copy assignment operator replaces the contents of one container with a copy of another.
		 * This only works if swapping is done by moving, which requires a Move Constructor and Move Operator.
		 * "*this" is a reference to the object.
		 */
	}

	~Vector()
	{
		#if DEBUG
		cout << "Vector Destructor" << endl;
		#endif

		delete[] objects;

		/*
		 * The destructor's "delete[]" deallocates its memory.
		 */
	}

	Vector(Vector && rhs) : theSize{ rhs.theSize }, theCapacity{ rhs.theCapacity }, objects{ rhs.objects }
	{
		#if DEBUG
		cout << "Move Constructor" << endl;
		#endif

		rhs.objects = nullptr;
		rhs.theSize = 0;
		rhs.theCapacity = 0;

		/*
		 * Releaseing the pointer from the source object reduces redundancy. The destructor won't have to
		 * free memory multiple times.
		 */
	}

	Vector & operator = (Vector && rhs)
	{
		#if DEBUG
		cout << "Move Assignment" << endl;
		#endif

		std::swap(theSize, rhs.theSize);
		std::swap(theCapacity, rhs.theCapacity);
		std::swap(objects, rhs.objects);
		return *this;

		/*
		 * Swap the data from the source object.
		 */
	}

	void resize(int newSize)
	{
		#if DEBUG
		cout << "Resize Routine| Is newSize=" << newSize << " > theCapacity=" << theCapacity << "?" << endl;
		#endif

		if (newSize > theCapacity)
		{
			#if DEBUG
			cout << "Resize Routine| Yes. Reserving for newSize." << endl;
			#endif

			reserve(newSize * 2);
		}
		#if DEBUG
		else
		{
			cout << "Resize Routine| No. Reserving space not needed." << endl;
		}
		#endif

		theSize = newSize;

		#if DEBUG
		cout << "Resize Routine| theSize=" << theSize << endl;
		#endif

		/*
		 * The resize method checks if the requested newSize is larger than the current capacity.
		 * If so, the reserve() method is called (This can be found below).
		 * Reguardless of the newSize request being larger or smaller than the current size,
		 * theSize is changed to the requested size. 
		 */
	}

	void reserve(int newCapacity)
	{
		#if DEBUG
		cout << "Reserve Routine| Is newCapacity=" << newCapacity << " < theSize" << theSize << "?" << endl;
		#endif

		if (newCapacity < theSize)
		{
			#if DEBUG
			cout << "Reserve Routine| Yes. Reserve request ignored." << endl;
			#endif

			return;
		}
		#if DEBUG
		else
		{
			cout << "Reserve Routine| No. No space needs to be reserved." << endl;
		}
		#endif

		Object *newArray = new Object[newCapacity];
		for (int k = 0; k < theSize; ++k)
		{
			newArray[k] = std::move(objects[k]);
		}
		theCapacity = newCapacity;
		std::swap(objects, newArray);
		delete[] newArray;

		#if DEBUG
		cout << "Reserve Routine| theCapacity=" << theCapacity << endl;
		cout << "Reserve Routine| Swapped objects and newArray." << endl;
		cout << "Reserve Routine| newArray deleted." << endl;
		#endif

		/*
		 * theSize and theCapacity are two distinct identities.
		 * theSize is the maximum referenceable index.
		 * theCapacity is the actual memory allocation.
		 * If the request for the newCapacity is smaller than the current size, there is no need to reserve
		 * more space, and the function returns. If space does need to be reserved, a new Array with the
		 * newCapacity is created. Then the contents of the preexisting array is copied to the newArray.
		 * theCapacity is set to the newCapacity. The newArray's contents is then swapped into the objects
		 * and then deleted.
		 */
	}

	Object & operator[] (int index)
	{
		#if DEBUG
		cout << "Subscript Operator" << endl;
		#endif

		return objects[index];

		/*
		 * This function allows the overload of the subscript operator ([]) so the objects can be accessed.
		 * With this implementation, the subscipt operator can be used to return the element from position
		 * "index" in objects.
		 */
	}

	const Object & operator[] (int index) const
	{
		#if DEBUG
		cout << "Subscript Operator (Const)" << endl;
		#endif

		return objects[index];

		/*
		 * This function works exaclty like the one above, but added "const" to allow const objects to use
		 * the subscript operator as well.
		 */
	}

	bool empty()
	{
		#if DEBUG
		cout << "Is empty?| " << (size() == 0) << endl;
		#endif

		return size() == 0;

		/*
		 * This function checks if the vector is empty by calling size() and comparing the size to 0.
		 * Because this function is a bool, it can be evaluated that 1 is "empty" and 0 means there is
		 * at least one object.
		 */
	}

	int size() const
	{
		#if DEBUG
		cout << "theSize()| =" << theSize << endl;
		#endif

		return theSize;

		/*
		 * This function calls the private member "theSize" and returns the size of the referencable index.
		 */
	}

	int capacity() const
	{
		#if DEBUG
		cout << "theCapacity()| =" << theCapacity << endl;
		#endif

		return theCapacity;

		/*
		 * This function calls the private member "theCapacity" and returns the allocated space available.
		 */
	}

	void push_back(Object & x)
	{
		#if DEBUG
		cout << "Push Back| Does theSize=" << theSize << " = theCapacity=" << theCapacity << "?" << endl;
		#endif

		if (theSize == theCapacity)
		{
			#if DEBUG
			cout << "Push Back| Yes. Space must be reserved first." << endl;
			#endif

			reserve(2 * theCapacity + 1);
		}
		#if DEBUG
		else
		{
			cout << "Push Back| No. Continue." << endl;
		}
		#endif

		objects[theSize++] = std::move(x);

		#if DEBUG
		cout << "Push Back| Array indexed and theSize increased to " << theSize << endl;
		#endif

		/*
		 * push_back adds x to the end of the array. This function checks to see if this push back will
		 * create a bounds error by exceeding the capacity, and reserves more capacity if so. Then, x is
		 * moved as a reference in position theSize+1.
		 */
	}

	void push_back(const Object && x)
	{
		#if DEBUG
		cout << "Push Back| Does theSize=" << theSize << " = theCapacity=" << theCapacity << "?" << endl;
		#endif

		if (theSize == theCapacity)
		{
		#if DEBUG
			cout << "Push Back| Yes. Space must be reserved first." << endl;
		#endif

			reserve(2 * theCapacity + 1);
		}
		#if DEBUG
		else
		{
			cout << "Push Back| No. Continue." << endl;
		}
		#endif

		objects[theSize++] = x;

		#if DEBUG
		cout << "Push Back| Array indexed and theSize increased to " << theSize << endl;
		#endif

		/*
		 * Identical to the function above, but created to push back const objects. Rather than being moved,
		 * the value is being copied.
		 */
	}

	void pop_back()
	{
		--theSize;

		#if DEBUG
		cout << "Pop Back| Now theSize=" << theSize << endl;
		#endif

		/*
		 * Pop back erases the last position by decreasing the size by one.
		 */
	}

	const Object & back() const
	{
		return objects[theSize - 1];

		/*
		 * Returns the value of the last item in the array.
		 */
	}

	typedef Object * iterator;
	/*
	 * Declaration of the iterator type. iterator is another name for a pointer variable.
	 */

	typedef const Object * const_iterator;
	/*
	 * Declaration of the const_iterator type. const_iterator is another name for a pointer variable.
	 */

	iterator begin()
	{
		return &objects[0];

		/*
		 * Returns the memory address that represents the first array position.
		 */
	}

	const_iterator begin() const
	{
		return &objects[0];

		/*
		 * Comment
		 */
	}

	iterator end()
	{
		return &objects[size()];

		/*
		 * Returns the memory address that represents the first invalid array position.
		 */
	}

	const_iterator end() const
	{
		return &objects[size()];

		/*
		 * Comment
		 */
	}

	static const int SPARE_CAPACITY = 10;
	/*
	 * When constrructed, the capacity has a default value stored here. This allows a few push_backs to
	 * occur before increasing the capacity. This default value can be changed to fit the programmer's needs.
	 */

private:
	int theSize;
	int theCapacity;
	Object * objects;
	/*
	 * The private data members hold the size, capacity, and the primitive array.
	 */
};

/*
 * This is a test program to see how this vector works.
 * Try compiling with debug strings to see step by step.
 * g++ -D DEBUG -std=c++1y vector.cpp -o vector
 */

int main()
{
	cout << "Create Vector T" << endl;
	Vector<int> T(5);

	cout << "Reserve more space" << endl;
	T.reserve(10);

	cout << "Push back more items than space available" << endl;
	for (int i = 0; i < 25; i++)
	{
		T.push_back(i);
	}

	cout << "Check the size and capacity" << endl;
	T.size();
	T.capacity();

	cout << "Pop back items until empty" << endl;
	while (!T.empty())
	{
		T.pop_back();
	}


	//system("pause");
	return 0;
}