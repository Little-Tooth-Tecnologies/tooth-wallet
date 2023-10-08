import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.littletoothwallet.LazyRegister1Fragment
import com.example.littletoothwallet.LazyRegister2Fragment
import com.example.littletoothwallet.LazyRegister3Fragment


class RegisterPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return LazyRegister1Fragment()
            1 -> return LazyRegister2Fragment()
            2 -> return LazyRegister3Fragment()
            else -> return Fragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
