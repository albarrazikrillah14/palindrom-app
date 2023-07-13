import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.medomeckz.palindromapp.R
import com.medomeckz.palindromapp.databinding.FragmentAnswerBinding

class AnswerFragment : DialogFragment() {
    private var _binding: FragmentAnswerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        btnClose()
    }

    private fun btnClose() {
        binding.btnClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun setUp() {
        val text = arguments?.getString(TEXT)
        val result = arguments?.getBoolean(RESULT)

        Log.d("TAG_FRAGMENTX", "$text $result")
        binding.tvText.text = text

        if(result as Boolean) binding.tvDesc.text = String.format(getString(R.string.result), "Adalah")
        else binding.tvDesc.text = String.format(getString(R.string.result), "Bukan")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TEXT = "text"
        const val RESULT = "result"
    }
}
