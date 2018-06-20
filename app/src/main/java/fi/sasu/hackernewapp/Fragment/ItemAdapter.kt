package fi.sasu.hackernewapp.Fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter

class ItemAdapter : FragmentPagerAdapter() {

val itemFragmentList=ArrayList<Fragment>()

    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment {
       return itemFragmentList.get(position)
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
       return itemFragmentList.size
    }


    fun  addFragment(fragment: Fragment){
        itemFragmentList.add(fragment)

    }


}