package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class SafariLogPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val animals: List<Animal>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = animals.size

    override fun createFragment(position: Int): Fragment {
        return AnimalFragment.newInstance(animals[position])
    }
}

// Create a Fragment to hold each animal item
class AnimalFragment : Fragment() {
    companion object {
        private const val ARG_ANIMAL = "animal"

        fun newInstance(animal: Animal): AnimalFragment {
            val fragment = AnimalFragment()
            val args = Bundle()
            args.putParcelable(ARG_ANIMAL, animal)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_safari_log, container, false)

        val animal = arguments?.getParcelable<Animal>(ARG_ANIMAL)

        animal?.let {
            view.findViewById<ImageView>(R.id.animal_image_view).setImageResource(it.imageResId)
            view.findViewById<TextView>(R.id.animal_name_text_view).text = it.name
            view.findViewById<TextView>(R.id.animal_description_text_view).text = it.description
        }

        return view
    }
}