package fi.sasu.hackernewapp.fragment


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ItemAdapter(fm: FragmentManager) : FragmentPagerAdapter( fm) {

    private val itemFragmentList=LinkedHashMap<Fragment, String>()

    override fun getItem(position: Int): Fragment {
        return itemFragmentList.keys.elementAt(position)
    }

    override fun getCount(): Int {
       return itemFragmentList.size
    }


    fun  addFragment(fragment: Fragment,title:String){
        itemFragmentList[fragment]=title

    }
}