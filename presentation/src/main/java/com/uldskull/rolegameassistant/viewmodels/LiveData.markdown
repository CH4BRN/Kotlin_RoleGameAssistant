#LiveData

*Note:* If you use LiveData independently from Room, you have to manage updating the data. LiveData
has no publicly available methods to update the stored data.


If you want to update data stored within LiveData, you must use MutableLiveData instead of 

LiveData. The MutableLiveData class has two public methods that allow you to set the value of a

 LiveData object, setValue(T) and postValue(T). Usually, MutableLiveData is used within the 
 
 ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.
 
