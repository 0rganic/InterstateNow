import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interstatenow.response.RestAreaChild
import com.example.interstatenow.response.RestAreaParent
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _parentList = MutableLiveData<List<RestAreaParent>>()
    val parentList: LiveData<List<RestAreaParent>> = _parentList

    private var _childList = MutableLiveData<List<RestAreaChild>>()
    val childList: LiveData<List<RestAreaChild>> = _childList



    fun setParentList(list: List<RestAreaParent>) {
        _parentList.value = list
    }

    fun fetchData() {
        val parentList = mutableListOf<RestAreaParent>()
        val childList = mutableListOf<RestAreaChild>()

        // Initialize Firebase
        FirebaseApp.initializeApp(getApplication())

        val db = FirebaseFirestore.getInstance()
        val document = db.collection("test_db").document("4HSAimb8PVrHtrzO9Lt2")

        document.get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    val data = result.data
                    if (data != null) {
                        val listToll = data["list_toll"] as List<Map<String, Any>>?
                        if (listToll != null) {
                            for (toll in listToll) {
                                val name = toll["name"] as String?
                                val id = toll["id"] as String?
                                val restAreaList = mutableListOf<RestAreaChild>()

                                val listRestArea = toll["list_restArea"] as List<Map<String, Any>>?
                                if (listRestArea != null) {
                                    for (restArea in listRestArea) {
                                        val restAreaName = restArea["name"] as String?
                                        val restAreaId = restArea["id_restArea"] as String?
                                        val restAreaImage = restArea["image"] as String?
                                        val restAreaChild =
                                            RestAreaChild(restAreaId, restAreaName, restAreaImage)
                                        restAreaList.add(restAreaChild)
                                        childList.add(restAreaChild)
                                    }
                                }

                                val parent = RestAreaParent(id, name, restAreaList)
                                parentList.add(parent)
                            }
                        }
                    }
                    _parentList.value = parentList
                    _childList.value = childList
                } else {
                    // Handle document not found case
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure case
            }
    }

    fun filterList(parentList: List<RestAreaParent>, query: String?): List<RestAreaParent> {
        return if (query != null) {
            val filteredList = mutableListOf<RestAreaParent>()

            for (parent in parentList) {
                if (parent.name?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(parent)
                }
            }

            filteredList
        } else {
            parentList
        }
    }
}