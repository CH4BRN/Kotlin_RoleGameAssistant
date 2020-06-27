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
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill


/**
 *   Class "SkillsAdapter" :
 *   Adapter for skills recycler view.
 **/
class OccupationSkillsAdapter constructor(
    val context: Context,
    private val occupationSkillsRecyclerViewFragment_buttonListenerCustom: CustomAdapterButtonListener<DomainSkillToFill>
) : RecyclerView.Adapter<OccupationSkillsAdapter.OccupationSkillsViewHolder>() {
    companion object {
        private const val TAG = "OccupationSkillsAdapter"
    }

    /**
     * Checked position
     */
    var checkedPosition: Int = 0


    /**
     * Skills list
     */
    var occupationSkills = emptyList<DomainSkillToFill?>()
        set(value) {
            Log.d(TAG, "skills : ${value.size}")
            field = value
        }

    /**
     * Set the list containing skills to display
     */
    internal fun setOccupationFilledSkills(skills: List<DomainSkillToFill?>?) {
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
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.fragment_occupationskill_recyclerview_item,
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
        private var tvOccupationSkillName: TextView? = itemView.findViewById(R.id.tv_occupationSkillName)
        private var tvOccupationSkillBase: TextView? = itemView.findViewById(R.id.tv_occupationSkillBase)
        private var tvOccupationSkillAdd: TextView? = itemView.findViewById(R.id.tv_occupationSkillAdd)
        private var tvOccupationSkillTotal: TextView? = itemView.findViewById(R.id.tv_occupationSkillTotal)
        private var tvOccupationSkillPlus:TextView? = itemView.findViewById(R.id.tv_occupationSkillPlus)

        /**
         * Bind the view holder
         */
        fun bind(skill: DomainSkillToFill?) {
            tvOccupationSkillName?.text = skill?.skillName
            var base: Int? = 0
            base = occupationSkills[adapterPosition]?.filledSkillBase
            var add: Int? = 0
            add = calculateAdd(occupationSkills[adapterPosition])


            if (base != null && add != null) {
                val total = base + add
                tvOccupationSkillTotal?.text = total.toString()
            }
            tvOccupationSkillAdd?.text = add.toString()

            tvOccupationSkillBase?.text =
                base.toString()

            if (checkedPosition == -1) {
                //  Initial
                itemView.background =
                    context.getDrawable(R.drawable.my_recycler_view_cell_background)
            } else {
                //  Selected
                if (checkedPosition == adapterPosition) {
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_selected_cell_background)
                    tvOccupationSkillName?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    tvOccupationSkillBase?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    tvOccupationSkillAdd?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    tvOccupationSkillTotal?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    tvOccupationSkillPlus?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                } else {
                    //  Not selected
                    itemView.background =
                        context.getDrawable(R.drawable.my_recycler_view_cell_background)
                    tvOccupationSkillName?.setTextColor(context.resources.getColor(R.color.colorPrimary))
                    tvOccupationSkillBase?.setTextColor(context.resources.getColor(R.color.colorPrimary))
                    tvOccupationSkillAdd?.setTextColor(context.resources.getColor(R.color.colorPrimary))
                    tvOccupationSkillTotal?.setTextColor(context.resources.getColor(R.color.colorPrimary))
                    tvOccupationSkillPlus?.setTextColor(context.resources.getColor(R.color.colorPrimary))
                }
            }


            itemView.setOnClickListener {
                itemView.background =
                    context.getDrawable(R.drawable.my_recycler_view_selected_cell_background)
                tvOccupationSkillName?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                tvOccupationSkillBase?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                tvOccupationSkillAdd?.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                tvOccupationSkillTotal?.setTextColor(context.resources.getColor(R.color.textColorPrimary))

                occupationSkillsRecyclerViewFragment_buttonListenerCustom.itemPressed(occupationSkills[adapterPosition], adapterPosition)
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
    fun getSelected(): DomainSkillToFill? {
        if (checkedPosition != -1) {
            return occupationSkills[checkedPosition]
        }
        return null
    }



    /**
     * Calculates a skill's add value.
     */
    private fun calculateAdd(current: DomainSkillToFill?): Int? {
        var add: Int? = 0

        Log.d(TAG, "current : $current")

        if (current?.filledSkillTensValue != null && current.filledSkillUnitsValue != null) {
            val tens = current.filledSkillTensValue.toString()
            Log.d(TAG, "tens : $tens")
            val units = current.filledSkillUnitsValue.toString()
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