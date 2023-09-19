import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.littletoothwallet.CadastroEtapa1Fragment
import com.example.littletoothwallet.CadastroEtapa2Fragment

class CadastroPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return CadastroEtapa1Fragment()
            1 -> return CadastroEtapa2Fragment()
            else -> return Fragment()
        }
    }

    override fun getCount(): Int {
        // Número total de etapas do cadastro
        return 2
    }
}
