// CustomOnItemSelectedListener.kt created by UldSkull - 17/06/2020

package com.uldskull.rolegameassistant.fragments.core.listeners

import android.view.View
import android.widget.AdapterView

/**
Class "CustomOnItemSelectedListener"

Custom onItemSelectedListener that only the "onItemSelected" method is abstract
 */
abstract class CustomOnItemSelectedListener:AdapterView.OnItemSelectedListener{
    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Ignore
    }

    /**
     *
     * Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.
     *
     * Implementers can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    abstract override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)

}