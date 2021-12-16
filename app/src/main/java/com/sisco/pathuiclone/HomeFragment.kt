package com.sisco.pathuiclone

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton
import com.sisco.pathuiclone.HomeFragment.ViewHolder
import com.sisco.pathuiclone.adapter.ParallaxRecylerAdapter
import com.sisco.pathuiclone.model.Data
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltipUtils
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var actionButton: FloatingActionButton
    private lateinit var itemIcon: ImageView
    private lateinit var itemIcon2: ImageView
    private lateinit var itemIcon3: ImageView
    private lateinit var itemIcon4: ImageView
    private lateinit var itemIcon5: ImageView
    private lateinit var itemIcon6: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab = ImageView(requireActivity())
        fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_plus))

        val starParams: FloatingActionButton.LayoutParams =
            FloatingActionButton.LayoutParams(
                requireActivity().resources.getDimensionPixelSize(R.dimen.red_action_button_size),
                requireActivity().resources.getDimensionPixelSize(
                    R.dimen.red_action_button_size
                )
            )
        starParams.setMargins(
            requireActivity().resources.getDimensionPixelSize(R.dimen.red_action_button_size),
            requireActivity().resources.getDimensionPixelSize(R.dimen.red_action_button_size),
            requireActivity().resources.getDimensionPixelSize(R.dimen.red_action_button_size),
            requireActivity().resources.getDimensionPixelSize(R.dimen.red_action_button_size)
        )
        fab.layoutParams = starParams

        actionButton = FloatingActionButton.Builder(requireActivity())
            .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
            .setBackgroundDrawable(requireActivity().getDrawable(R.drawable.bg_fab))
            .setLayoutParams(starParams)
            .setContentView(fab)
            .build()

        val itemBuilder = SubActionButton.Builder(requireActivity())
        itemIcon = ImageView(requireActivity())
        itemIcon2 = ImageView(requireActivity())
        itemIcon3 = ImageView(requireActivity())
        itemIcon4 = ImageView(requireActivity())
        itemIcon5 = ImageView(requireActivity())
        itemIcon6 = ImageView(requireActivity())

        itemIcon.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_song))
        val button1 = itemBuilder.setContentView(itemIcon).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()
        itemIcon2.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_location))
        val button2 = itemBuilder.setContentView(itemIcon2).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()
        itemIcon3.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_camera))
        val button3 = itemBuilder.setContentView(itemIcon3).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()
        itemIcon4.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_shop))
        val button4 = itemBuilder.setContentView(itemIcon4).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()
        itemIcon5.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_message))
        val button5 = itemBuilder.setContentView(itemIcon5).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()
        itemIcon6.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_sleep))
        val button6 = itemBuilder.setContentView(itemIcon6).setBackgroundDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.bg_item_fab
            )
        ).build()

        val floating = FloatingActionMenu.Builder(requireActivity())
            .addSubActionView(button1)
            .addSubActionView(button2)
            .addSubActionView(button3)
            .addSubActionView(button4)
            .addSubActionView(button5)
            .addSubActionView(button6)
            .setStartAngle(180)
            .setEndAngle(360)
            .attachTo(actionButton)
            .build()

        floating.setStateChangeListener(object : FloatingActionMenu.MenuStateChangeListener {
            override fun onMenuOpened(p0: FloatingActionMenu?) {
                fab.rotation = 45f
            }

            override fun onMenuClosed(p0: FloatingActionMenu?) {
                fab.rotation = 0f
            }
        })

        rv = view.findViewById(R.id.rv)
        createAdapter()
    }

    private fun createAdapter() {
        val content: MutableList<Data> = ArrayList()
        for (i in 0..3) {
            content.add(
                Data(
                    activity?.getDrawable(R.drawable.img_avatar),
                    activity?.getDrawable(R.drawable.img_avatar2),
                    getString(
                        R.string.label_msg1
                    ),
                    3
                )
            )
            content.add(
                Data(
                    activity?.getDrawable(R.drawable.img_avatar3),
                    activity?.getDrawable(R.drawable.ic_not_gray),
                    getString(
                        R.string.label_msg2
                    ),
                    10
                )
            )
            content.add(
                Data(
                    activity?.getDrawable(R.drawable.img_avatar4),
                    activity?.getDrawable(R.drawable.img_avatar5),
                    getString(
                        R.string.label_msg3
                    ),
                    50
                )
            )
            content.add(
                Data(
                    activity?.getDrawable(R.drawable.img_avatar6),
                    activity?.getDrawable(R.drawable.ic_music),
                    getString(
                        R.string.label_msg4
                    ),
                    50
                )
            )
        }

        val adapter: ParallaxRecylerAdapter<Data> = object : ParallaxRecylerAdapter<Data>(content) {
            override fun onBindViewHolderImpl(
                viewHolder: RecyclerView.ViewHolder,
                adapter: ParallaxRecylerAdapter<Data>,
                i: Int
            ) {
                (viewHolder as HomeFragment.ViewHolder).txvInfo.text = content[i].message
                viewHolder.txvLike.text = content[i].like.toString()
                viewHolder.imgProfile.setImageDrawable(content[i].profile)
                viewHolder.txvTitle.visibility = View.GONE
                viewHolder.clComment.visibility = View.GONE
                if (i == 1){
                    viewHolder.imgAnotherProfile.visibility = View.GONE
                    viewHolder.imgAnotherProfileNone.visibility = View.VISIBLE
                    viewHolder.imgAnotherProfileNone.setImageDrawable(content[i].anotherProfile)
                }else{
                    viewHolder.imgSong.visibility = View.GONE
                    if (i == 3){
                        viewHolder.rootComment.visibility = View.VISIBLE
                        viewHolder.imgSong.visibility = View.VISIBLE
                        viewHolder.imgSong.setImageDrawable(activity?.getDrawable(R.drawable.img_song))
                        viewHolder.txvTitle.visibility = View.VISIBLE
                        viewHolder.txvTitle.text = getString(R.string.label_song)
                    }
                    viewHolder.imgAnotherProfile.visibility = View.VISIBLE
                    viewHolder.imgAnotherProfileNone.visibility = View.GONE
                    viewHolder.imgAnotherProfile.setImageDrawable(content[i].anotherProfile)
                }

                viewHolder.imgBgLike.setOnClickListener {
                    val tooltip = SimpleTooltip.Builder(requireActivity())
                        .anchorView(viewHolder.imgBgLike)
                        .gravity(Gravity.START)
                        .dismissOnOutsideTouch(false)
                        .dismissOnInsideTouch(false)
                        .modal(true)
                        .arrowDrawable(R.drawable.ic_arrow_black)
                        .contentView(R.layout.item_comment)
                        .focusable(true)
                        .build()

                    Log.i("TAG", "onBindViewHolderImpl: ${tooltip.isShowing}")
                    tooltip.show()

                    val imgLove = tooltip.findViewById<ImageView>(R.id.imglove)
                    val imgPersonLove = tooltip.findViewById<ImageView>(R.id.imgPersonLove)
                    imgLove.setOnClickListener {
                        imgPersonLove.visibility = View.VISIBLE
                        viewHolder.txvLike.text = content[i].like.plus(1).toString()
                        viewHolder.imgLove1.background = (requireActivity().getDrawable(R.drawable.bg_ic_love_red))
                        Handler().postDelayed({
                            tooltip.dismiss()
                        }, 500)
                    }
                }
            }

            override fun onCreateViewHolderImpl(
                viewGroup: ViewGroup,
                adapter: ParallaxRecylerAdapter<Data>,
                i: Int
            ): RecyclerView.ViewHolder {
                return ViewHolder(layoutInflater.inflate(R.layout.item_list, viewGroup, false))
            }

            override fun getItemCountImpl(adapter: ParallaxRecylerAdapter<Data>): Int {
                return content.size
            }
        }

        rv.layoutManager = LinearLayoutManager(requireActivity())
        val header: View = layoutInflater.inflate(R.layout.item_header, rv, false)
        adapter.setParallaxHeader(header, rv)
        adapter.data = content
        rv.adapter = adapter
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txvInfo: TextView = itemView.findViewById(R.id.txvInfo)
        var txvLike: TextView = itemView.findViewById(R.id.txvLove)
        var imgProfile: AppCompatImageView = itemView.findViewById(R.id.imgProfile)
        var imgAnotherProfile: AppCompatImageView = itemView.findViewById(R.id.imgAnotherProfile)
        var imgAnotherProfileNone: AppCompatImageView = itemView.findViewById(R.id.imgAnotherProfileNone)
        var imgSong: AppCompatImageView = itemView.findViewById(R.id.imgSong)
        var txvTitle: MaterialTextView = itemView.findViewById(R.id.txvTitle)
        var rootComment: ConstraintLayout = itemView.findViewById(R.id.root_comment)
        var imgBgLike: AppCompatImageView = itemView.findViewById(R.id.imgBgLike)
        var clComment: ConstraintLayout = itemView.findViewById(R.id.clComment)
        var imglove: ImageView = itemView.findViewById(R.id.imgLove2)
        var imgLove1: AppCompatImageView = itemView.findViewById(R.id.imgLove)
        var imgPersonLove: ImageView = itemView.findViewById(R.id.imgPersonLove)
    }

    override fun onPause() {
        super.onPause()
        actionButton.visibility = View.GONE
        itemIcon.visibility = View.GONE
        itemIcon2.visibility = View.GONE
        itemIcon3.visibility = View.GONE
        itemIcon4.visibility = View.GONE
        itemIcon5.visibility = View.GONE
        itemIcon6.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        actionButton.visibility = View.VISIBLE
        itemIcon.visibility = View.VISIBLE
        itemIcon2.visibility = View.VISIBLE
        itemIcon3.visibility = View.VISIBLE
        itemIcon4.visibility = View.VISIBLE
        itemIcon5.visibility = View.VISIBLE
        itemIcon6.visibility = View.VISIBLE
    }
}