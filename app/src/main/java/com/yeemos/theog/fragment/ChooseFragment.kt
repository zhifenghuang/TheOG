package com.yeemos.theog.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.common.lib.fragment.BaseFragment
import com.common.lib.utils.BaseConstants
import com.common.lib.utils.BaseUtils
import com.yeemos.theog.R
import com.yeemos.theog.bean.ItemBean
import com.yeemos.theog.contract.ChooseContract
import com.yeemos.theog.presenter.ChoosePresenter
import kotlinx.android.synthetic.main.activity_select_religious_beliefs.*
import kotlinx.android.synthetic.main.layout_top_bar.*


class ChooseFragment : BaseFragment<ChoosePresenter>(),
    ChooseContract.View {

    var mType: Int = 0;  //0表示选择宗教1表示选择宠物
    var mSelectItem: ItemBean? = null


    override fun initView(view: View, savedInstanceState: Bundle?) {
        setViewsOnClickListener(ivSwitch, ivVisible)
        mType = arguments?.getInt(BaseConstants.BUNDLE_EXTRA, 0)!!
        mSelectItem = arguments?.getSerializable(BaseConstants.BUNDLE_EXTRA_2) as ItemBean
        initView()
    }

    fun initView() {
        val array = getString(R.string.pblc_txt_religiousoptions).split(",")
        var tv: TextView
        var texts: List<String>
        var itemBean: ItemBean? = null
        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            BaseUtils.dp2px(activity!!, 40f)
        )
        flowLayout.removeAllViews()
        for (item in array) {
            tv = TextView(activity)
            tv.setPadding(BaseUtils.dp2px(activity!!, 20f), 0, BaseUtils.dp2px(activity!!, 20f), 0)
            tv.gravity = Gravity.CENTER
            texts = item.split("_")
            tv.text = texts[1]
            tv.setSingleLine()
            itemBean = ItemBean(texts[0].toInt(), texts[1])
            if (itemBean.id == -1) {
                itemBean.isSelected = true
                tv.setBackgroundResource(R.drawable.bg_item_select)
                tv.setTextColor(ContextCompat.getColor(activity!!, R.color.color_ff_ff_ff))
            } else {
                tv.setBackgroundResource(R.drawable.bg_item_unselect)
                tv.setTextColor(ContextCompat.getColor(activity!!, R.color.color_52_5a_65))
            }
            lp.rightMargin = BaseUtils.dp2px(activity!!, 6f)
            lp.topMargin = BaseUtils.dp2px(activity!!, 14f)
            flowLayout.addView(tv, lp)
            tv.tag = itemBean
            tv.setOnClickListener {
                val bean = it.tag as ItemBean
                bean.isSelected = !bean.isSelected
                if (bean.isSelected) {
                    it.setBackgroundResource(R.drawable.bg_item_select)
                    (it as TextView).setTextColor(
                        ContextCompat.getColor(
                            activity!!,
                            R.color.color_ff_ff_ff
                        )
                    )
                } else {
                    it.setBackgroundResource(R.drawable.bg_item_unselect)
                    (it as TextView).setTextColor(
                        ContextCompat.getColor(
                            activity!!,
                            R.color.color_52_5a_65
                        )
                    )
                }

            }
        }
    }


    override fun getPresenter(): ChoosePresenter {
        return ChoosePresenter(this);
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_select_religious_beliefs
    }

    override fun updateUI() {
        if (mType == 0) {
            setTextByServerKey(tvTitle, "mrbtm_ttl_religious")
            setTextByServerKey(tvName, "mrbtm_txt_whatreligious")
        } else {
            setTextByServerKey(tvTitle, "mrbtm_ttl_pets")
            setTextByServerKey(tvName, "mrbtm_txt_whatpets")
        }
    }


    override fun onClick(v: View?) {
        when (v) {
            ivSwitch -> {

            }
            ivVisible -> {

            }
        }
    }

}