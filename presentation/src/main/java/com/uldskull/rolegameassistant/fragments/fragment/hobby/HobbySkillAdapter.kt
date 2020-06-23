// File HobbySkillAdapter.kt
// @Author pierre.antoine - 11/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill

/**
 *   Class "HobbySkillAdapter" :
 *   Adapter for hobby skills
 **/
class HobbySkillAdapter internal constructor(
    val context: Context,
    private val hobbySkillsRecyclerViewFragment_buttonListenerCustom: CustomAdapterButtonListener<DomainSkillToFill>
) : RecyclerView.Adapter<HobbySkillAdapter.HobbySkillsViewHolder>() {

    companion object {
        private const val TAG = "HobbySkillAdapter"
    }

    /**
     * Current checked position
     */
    var checkedPosition: Int = 0

    /**
     * Layout inflater
     */
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Skills list  **/
    private var hobbySkills = emptyList<DomainSkillToFill?>()

    /**
     * View holder for hobby skills
     */
    inner class HobbySkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvHobbySkillName: TextView? = itemView.findViewById(R.id.tv_hobbySkillName)
        private var tvHobbySkillBase: TextView? = itemView.findViewById(R.id.tv_hobbySkillBase)
        private var tvHobbySkillAddTens: TextView? = itemView.findViewById(R.id.tv_hobbySkillAddTens)
        private var tvHobbySkillAddUnits: TextView? = itemView.findViewById(R.id.tv_hobbySkillAddUnits)
        private var tvHobbySkillTotal: TextView = itemView.findViewById(R.id.tv_hobbySkillTotal)

        init {
            /**
             * Set itemView onClickListener
             */
            itemView.setOnClickListener {
                itemView.background = context.getDrawable(R.drawable.my_recycler_view_selected_cell_background)
                tvHobbySkillName?.setTextColor(context.getColor(R.color.colorPrimary))

                hobbySkillsRecyclerViewFragment_buttonListenerCustom.itemPressed(hobbySkills[adapterPosition], adapterPosition)
                if(checkedPosition != adapterPosition){
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }
            }
        }

        /**
         * Bind the holder
         */
        fun bind(skill: DomainSkillToFill?) {
            Log.d("DEBUG$TAG", "Skill : $skill")
            //holder.tvHobbySkillName?.text = current.skillName
            tvHobbySkillName?.text = skill?.skillName
            tvHobbySkillBase?.text = skill?.filledSkillBase.toString()
            tvHobbySkillAddTens?.text = skill?.filledSkillTensValue.toString()
            tvHobbySkillAddUnits?.text = skill?.filledSkillUnitsValue.toString()
            tvHobbySkillTotal.text = skill?.filledSkillTotal.toString()

            if (checkedPosition == -1) {
                //  Initial
                itemView.background =
                    context.getDrawable(R.drawable.my_recycler_view_cell_background)
            } else {
                //  Selected
                if (checkedPosition == adapterPosition) {
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_selected_cell_background)
                } else {
                    //  Not selected
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_cell_background)
                }
            }


        }
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbySkillsViewHolder {
        val itemView = layoutInflater.inflate(R.layout.fragment_hobbyskill_recyclerview_item, parent, false)
        return HobbySkillsViewHolder((itemView))
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return hobbySkills.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: HobbySkillsViewHolder, position: Int) {
        holder.bind(hobbySkills[position])
    }

    /**
     * Set hobby skills
     */
    fun setHobbySkills(skills: List<DomainSkillToFill?>) {
        this.hobbySkills = skills
        notifyDataSetChanged()
    }
}