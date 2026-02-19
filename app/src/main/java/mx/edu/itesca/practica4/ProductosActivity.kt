package mx.edu.itesca.practica4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductosActivity : AppCompatActivity() {

    var menu: ArrayList<Product> = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Como en la imagen: recibe la opción
        var menuOption: String? = intent.getStringExtra("menuType")
        agregarProductos(menuOption)

        var listview: ListView = findViewById(R.id.listview) as ListView
        var adaptador: AdaptadorProductos = AdaptadorProductos(this, menu)
        listview.adapter = adaptador
    }

    fun agregarProductos(option: String?) {

        menu.clear()

        when (option) {

            "Antojitos" -> {
                menu.add(Product(
                    "Quesadillas",
                    R.drawable.quesadillas,
                    "Rellenas con su carne favorita, servidas con ensalada.",
                    6.29))

                menu.add(Product(
                    "Huaraches",
                    R.drawable.huaraches,
                    "Tortilla gruesa con frijoles, tu carne favorita, lechuga, queso fresco y crema.",
                    11.49))

                menu.add(Product(
                    "Gringas",
                    R.drawable.gringas,
                    "Tortilla de harina con queso, carne al pastor y piña.",
                    8.39))

                menu.add(Product(
                    "Sincronizada",
                    R.drawable.sincronizadas,
                    "Tortilla de harina rellena con queso y jamón.",
                    7.99))

                menu.add(Product(
                    "Sopes",
                    R.drawable.sopes,
                    "Tortilla gruesa cubierta de frijoles, tu carne favorita, lechuga, queso fresco y crema.",
                    3.99))

                menu.add(Product(
                    "Tostadas",
                    R.drawable.tostadas,
                    "Tortilla frita con frijoles, tu carne favorita, lechuga, queso fresco, crema y jitomate.",
                    4.59))
            }

            "Especialidades" -> {
                menu.add(Product(
                    "Mojarra Frita",
                    R.drawable.mojarra, // <-- debe existir este drawable
                    "Tilapia frita servida con lechuga, cebolla, jitomate, aguacate y tortillas.",
                    17.99))
            }

            else -> {
                // Por si no llega nada, deja Antojitos como default
                menu.add(Product(
                    "Quesadillas",
                    R.drawable.quesadillas,
                    "Rellenas con su carne favorita, servidas con ensalada.",
                    6.29))
            }
        }
    }

    private class AdaptadorProductos : BaseAdapter {

        var productos = ArrayList<Product>()
        var contexto: Context? = null

        constructor(contexto: Context, producto: ArrayList<Product>) {
            this.productos = producto
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return productos.size
        }

        override fun getItem(position: Int): Any {
            return productos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var prod = productos[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.producto_view, null)

            var imagen = vista.findViewById(R.id.producto_img) as ImageView
            var nombre = vista.findViewById(R.id.producto_nombre) as TextView
            var desc = vista.findViewById(R.id.producto_desc) as TextView
            var precio = vista.findViewById(R.id.producto_precio) as TextView

            imagen.setImageResource(prod.image)
            nombre.setText(prod.name)
            desc.setText(prod.description)
            precio.setText("$${prod.price}")

            return vista
        }
    }
}
