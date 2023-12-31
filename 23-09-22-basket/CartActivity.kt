package com.example.wisefee.Cart

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisefee.MainActivity
import com.example.wisefee.Menu.MenuActivity
import com.example.wisefee.Payment.PaymentActivity
import com.example.wisefee.databinding.ActivityCartBinding
import com.example.wisefee.databinding.BuyDialogConfirmBinding


class CartActivity : AppCompatActivity() {
    private val cartItems = mutableListOf<CartItem>()
    private lateinit var cartAdapter: CartAdapter
    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기존의 cartItems를 불러온다.
        val existingCartItems = intent.getSerializableExtra("cartItems") as? ArrayList<CartItem>
        Log.i("cart", "이건 뭘까요? $existingCartItems")
        if (existingCartItems != null) {
            cartItems.addAll(existingCartItems)
        }

        cartAdapter = CartAdapter(cartItems)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = cartAdapter



        // 예시
//        val product = Product(1, "아메리카노",  "커피", null, 1000)
//        val cartItem = CartItem(product, quantity = 2)
//        cartItems.add(cartItem) //추가
//        cartAdapter.notifyDataSetChanged()  //리사이클뷰 변경적용
//        val product2 = Product(id = 2, name = "카페라떼", price = 3000)
//        val cartItem2 = CartItem(product2, quantity = 1)
//        cartItems.add(cartItem2) //추가
//        cartAdapter.notifyDataSetChanged()  //리사이클뷰 변경적용


        // 뒤로가기 버튼 (2023-09-09)
        binding.goBackButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java) //갈곳
            intent.putExtra("cartItems", existingCartItems) //현재 장바구니에 담겨진 항목들
            startActivity(intent) //인텐트 이동
        }
        // 구매 버튼
        binding.checkoutButton.setOnClickListener {
            showBuyDialog()
        }

        updateTotalPrice() // 초기화 시 총 금액 업데이트

    }

    //초기화 시 총 금액 업데이트
    private fun updateTotalPrice() {
        var totalPrice = 0
        var total = 0
        var quantity = 0
        for (cartItem in cartItems) {
            totalPrice += cartItem.product.productPrice * cartItem.quantity
            quantity += cartItem.quantity
        }
        total = totalPrice + 1000
        binding.priceTextView.text = "${totalPrice}원"
        binding.totalPriceTextView.text = "${total}원"
        binding.countTextView.text = "총 ${quantity}개"
    }

    //구매여부 버튼
    private fun showBuyDialog(){
        val dialogBinding = BuyDialogConfirmBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)

        val alertDialog = dialogBuilder.create()

        dialogBinding.btnYes.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java )
            startActivity(intent)
        }

        dialogBinding.btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}