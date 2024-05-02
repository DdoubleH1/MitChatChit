import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.chatapp.R
import com.example.chatapp.presentation.navigation.AppNavigation

object Spanner {
    fun spanString(
        spanTv: TextView,
        textView: String,
        resources: Resources,
        navCallBack: () -> Unit
    ) {
        val spanTvString = spanTv.text.toString()
        val spannableString = SpannableString(spanTvString)
        spannableString.apply {
            setSpan(
                ForegroundColorSpan(
                    resources.getColor(
                        R.color.primary_color_light,
                        null
                    )
                ),
                spanTvString.indexOf(textView),
                spanTvString.indexOf(textView) + textView.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            setSpan(
                StyleSpan(Typeface.BOLD),
                spanTvString.indexOf(textView),
                spanTvString.indexOf(textView) + textView.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        navCallBack.invoke()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                }, spanTvString.indexOf(textView),
                spanTvString.indexOf(textView) + textView.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        spanTv.text = spannableString
        spanTv.movementMethod = LinkMovementMethod.getInstance()
    }

}