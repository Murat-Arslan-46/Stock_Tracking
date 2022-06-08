package com.marslan.contract_acceptor

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.ScrollHandle
import com.marslan.contract_acceptor.databinding.ContractAcceptorBinding
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

class ContractAcceptor : Fragment() {
    private lateinit var binding: ContractAcceptorBinding

    private var listener: (Boolean) -> Unit = {}
    private val contracts = arrayListOf<ContractModel>()
    private var toolbarColor = Color.GREEN
    private var textColor = Color.WHITE
    private var approveColor = toolbarColor
    private var continueColor = approveColor
    private var toolbarVisibility = true
    private var currentPdf = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContractAcceptorBinding.inflate(layoutInflater, container, false)
        setView()
        return binding.root
    }

    private fun cancelForm() {
        listener(false)
        binding.cancel.visibility = View.GONE
        binding.ok.visibility = View.GONE
        binding.pdfViewer.recycle()
    }

    private fun okForm() {
        binding.cancel.visibility = View.GONE
        binding.ok.visibility = View.GONE
        listener(true)
    }

    private fun continueForm() {
        currentPdf += 1
        loadPdf(
            contracts[currentPdf - 1].pdfUrl,
            this::cancelForm,
            if (contracts.size > currentPdf) this::continueForm else this::okForm
        )
        binding.toolbarTitle.text = contracts[currentPdf - 1].title
    }

    private fun setView() {
        binding.root.visibility = if (toolbarVisibility) View.VISIBLE else View.GONE
        binding.toolbarBack.setOnClickListener { cancelForm() }
        binding.toolbarBack.imageTintList = ColorStateList.valueOf(textColor)
        binding.toolbarTitle.setTextColor(textColor)
        binding.toolbar.setBackgroundColor(toolbarColor)
        if (!contracts.isNullOrEmpty())
            continueForm()
        else
            okForm()
    }

    private fun loadPdf(url: String?, cancel: () -> Unit, ok: () -> Unit) {
        binding.progress
        binding.progress.visibility = View.VISIBLE
        binding.ok.text =
            if (contracts.size <= currentPdf)
                requireContext().getString(R.string.approve)
            else
                requireContext().getString(R.string.continue_text)
        binding.ok.backgroundTintList =
            if (contracts.size <= currentPdf)
                ColorStateList.valueOf(approveColor)
            else
                ColorStateList.valueOf(continueColor)
        binding.cancel.text = requireContext().getString(R.string.not_approve)
        binding.cancel.setOnClickListener { cancel() }
        binding.ok.setOnClickListener { ok() }
        binding.ok.visibility = View.GONE
        binding.cancel.visibility = View.GONE
        val scrollHandle = object : ScrollHandle {
            override fun setScroll(position: Float) {}
            override fun setupLayout(pdfView: PDFView?) {
                binding.progress.visibility = View.GONE
                if (binding.pdfViewer.pageCount <= 1) {
                    binding.ok.visibility = View.VISIBLE
                    binding.cancel.visibility =
                        if (contracts.size <= currentPdf) View.VISIBLE else View.GONE
                }
            }

            override fun destroyLayout() {}
            override fun setPageNum(pageNum: Int) {
                if (binding.pdfViewer.pageCount == pageNum) {
                    binding.ok.visibility = View.VISIBLE
                    binding.cancel.visibility =
                        if (contracts.size <= currentPdf) View.VISIBLE else View.GONE
                }
            }

            override fun shown() = false
            override fun show() {}
            override fun hide() {}
            override fun hideDelayed() {}
        }
        thread {
            try {
                val urlConnection: HttpURLConnection =
                    URL(url).openConnection() as HttpsURLConnection
                if (urlConnection.responseCode == 200) {
                    binding.pdfViewer
                        .fromStream(BufferedInputStream(urlConnection.inputStream))
                        .scrollHandle(scrollHandle)
                        .load()
                }
            } catch (e: IOException) {
                requireActivity().runOnUiThread {
                    binding.progress.visibility = View.GONE
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    fun show(fragmentManager: FragmentManager, containerId: Int = R.id.content) =
        fragmentManager.beginTransaction().add(containerId,this)

    companion object {
        private fun newInstance(
            contracts: ArrayList<ContractModel>,
            toolbarColor: Int,
            textColor: Int,
            approveColor: Int,
            continueColor: Int,
            toolbarVisibility: Boolean,
            listener: (Boolean) -> Unit = {}
        ): ContractAcceptor {
            return ContractAcceptor().apply {
                this.contracts.addAll(contracts)
                this.toolbarVisibility = toolbarVisibility
                this.toolbarColor = toolbarColor
                this.textColor = textColor
                this.approveColor = approveColor
                this.continueColor = continueColor
                this.listener = listener
                this.currentPdf = 0
            }
        }
    }

    data class ContractModel(
        val pdfUrl: String,
        val title: String,
        val color: Int = Color.WHITE
    )

    class Builder(list: List<ContractModel>) {

        private var listener: (Boolean) -> Unit = {}
        private val contracts = arrayListOf<ContractModel>()
        private var toolbarColor = Color.GREEN
        private var textColor = Color.WHITE
        private var approveColor = toolbarColor
        private var continueColor = approveColor
        private var toolbarVisibility = true

        init {
            contracts.clear()
            this.contracts.addAll(list)
        }

        fun setToolbarVisibility(visible: Boolean): Builder {
            this.toolbarVisibility = visible
            return this
        }

        fun setColor(color: Int): Builder {
            this.toolbarColor = color
            return this
        }

        fun setTextColor(color: Int): Builder {
            this.textColor = color
            return this
        }

        fun setApproveColor(color: Int): Builder {
            this.approveColor = color
            return this
        }

        fun setContinueColor(color: Int): Builder {
            this.continueColor = color
            return this
        }

        fun setApproveListener(listener: (Boolean) -> Unit): Builder {
            this.listener = listener
            return this
        }

        fun build() = ContractAcceptor.newInstance(
            contracts,
            toolbarColor,
            textColor,
            approveColor,
            continueColor,
            toolbarVisibility,
            listener
        )
    }
}