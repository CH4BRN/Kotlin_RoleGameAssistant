// File SkillsAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill


/**
 *   Class "SkillsAdapter" :
 *   Adapter for skills recycler view.
 **/
class OccupationSkillsAdapter constructor(
    val context: Context,
    private val occupationSkillsRecyclerViewFragment_buttonListener: AdapterButtonListener<DomainFilledSkill>
) : RecyclerView.Adapter<OccupationSkillsAdapter.OccupationSkillsViewHolder>() {
    //: CustomRecyclerViewAdapter(context)
    companion object {
        private const val TAG = "OccupationSkillsAdapter"
    }


    var checkedPosition: Int = 0

    /**
     * Inflater
     */
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    /**
     * Skills list
     */
    var occupationSkills = emptyList<DomainFilledSkill?>()
        set(value) {
            Log.d(TAG, "skills : ${value.size}")
            field = value
        }

    /**
     * Set the list containing skills to display
     */
    internal fun setOccupationFilledSkills(skills: List<DomainFilledSkill?>?) {
        Log.d(TAG, "setSkills")
        if (skills != null) {
            skills.forEach { skill ->
                kotlin.run {
                    Log.d(TAG, "skill : $skill")
                }
            }
            this.occupationSkills = skills
            notifyDataSetChanged()
        }

    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccupationSkillsViewHolder {
        var view: View = LayoutInflater.from(context).inflate(
            R.layout.recyclerview_item_occupationskill,
            parent, false
        )
        return OccupationSkillsViewHolder(view)
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
    override fun onBindViewHolder(holder: OccupationSkillsViewHolder, position: Int) {
        Log.d("DEBUG", "onBindViewHolder \n position : $position")
        Log.d("DEBUG", "occupationSkills.size : ${occupationSkills.size}")
        holder.bind(occupationSkills[position])
    }


    /**
     * Number of item ine the list *
     */
    override fun getItemCount(): Int = occupationSkills.size


    /**
     * Custom view-holder
     */
    inner class OccupationSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layoutOccupationSkill: LinearLayout? = itemView.findViewById(R.id.occupationSkill_row)
        var tvOccupationSkillName: TextView? = itemView.findViewById(R.id.tv_occupationSkillName)
        var tvOccupationSkillBase: TextView? = itemView.findViewById(R.id.tv_occupationSkillBase)
        var tvOccupationSkillAdd: TextView? = itemView.findViewById(R.id.tv_occupationSkillAdd)
        var tvOccupationSkillTotal: TextView? = itemView.findViewById(R.id.tv_occupationSkillTotal)
        var tvOccupationSkillPlus: TextView? = itemView.findViewById(R.id.tv_occupationSkillPlus)
        var tvOccupationSkillSeparator: TextView? =
            itemView.findViewById(R.id.tv_occupationSkillSeparator)

        /**
         * Bind the view holder
         */
        fun bind(skill: DomainFilledSkill?) {
            tvOccupationSkillName?.text = skill?.skillName
            var base: Int? = 0
            base = occupationSkills[adapterPosition]?.filledSkillBase
            var add: Int? = 0
            add = calculateAdd(occupationSkills[adapterPosition])


            if (base != null && add != null) {
                var total = base + add
                tvOccupationSkillTotal?.text = total.toString()
            }
            tvOccupationSkillAdd?.text = add.toString()

            tvOccupationSkillBase?.text =
                base.toString()

            if (checkedPosition == -1) {
                //  Initial
                itemView.background =
                    context.getDrawable(R.drawable.my_recycler_view_cell_backrgound)
            } else {
                //  Selected
                if (checkedPosition == adapterPosition) {
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_cell_backrgound)
                } else {
                    //  Not selected
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_cell_backrgound)
                }
            }


            itemView.setOnClickListener {
                itemView.background =
                    context.getDrawable(R.drawable.my_recycler_view_selected_cell_background)
                tvOccupationSkillName?.setTextColor(context.getColor(R.color.colorPrimary))

                occupationSkillsRecyclerViewFragment_buttonListener.itemPressed(occupationSkills[adapterPosition], adapterPosition)
                // tvOccupationSkillTotal?.visibility = View.VISIBLE
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }

            }

        }

    }

    /**
     * Get selected item.
     */
    fun getSelected(): DomainFilledSkill? {
        if (checkedPosition != -1) {
            return occupationSkills[checkedPosition]
        }
        return null
    }



    /**
     * Calculates a skill's add value.
     */
    private fun calculateAdd(current: DomainFilledSkill?): Int? {
        var add: Int? = 0

        Log.d(TAG, "current : $current")

        if (current?.filledSkillTensValue != null && current.filledSkillUnitsValue != null) {
            var tens = current.filledSkillTensValue.toString()
            Log.d(TAG, "tens : $tens")
            var units = current.filledSkillUnitsValue.toString()
            Log.d(TAG, "units : $units")

            try {
                add = "$tens$units".toInt()
                Log.d(TAG, "add : $add")

            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "add conversion failed")
                e.printStackTrace()
                throw e
            }
        }
        return add
    }


}