package com.example.dice

import PlayerScoreData
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private var playerList: MutableList<PlayerScoreData> = mutableListOf()
    private var usedNames: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = PlayerScoreAdapter(playerList)
        recyclerView.adapter = adapter

        addNewPlayer(getRandomName()) // Add an initial empty player

        val addButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        addButton.setOnClickListener {
            addNewPlayer(getRandomName())
        }

        val calcButton: FloatingActionButton = findViewById(R.id.floatingActionButton3)
        calcButton.setOnClickListener {
            val adapter = recyclerView.adapter as? PlayerScoreAdapter
            adapter?.let {
                for (i in 0 until it.itemCount) {
                    val player = it.playerList[i]
                    player.score = it.calculateScore(player)
                }
                it.notifyDataSetChanged()
                it.toggleScoreVisibility() // Toggle score visibility after score update
            }
        }
    }

    private fun addNewPlayer(name: String) {
        val newPlayer = PlayerScoreData(playerName = name, n = 0, r = 0, j = 0, q = 0, k = 0, a = 0)
        playerList.add(newPlayer)
        recyclerView.adapter?.notifyItemInserted(playerList.size - 1)
    }


    private fun getRandomName(): String {
        val names = listOf(
            "Napoleón", "Gandhi", "Pelé", "John Cena", "Darth Vader", "Cleopatra", "Albert Einstein", "Michael Jackson",
            "Leonardo da Vinci", "Serena Williams", "Marilyn Monroe", "Winston Churchill",
            "Elon Musk", "Beyoncé", "Steve Jobs", "William Shakespeare", "Marie Curie", "Malala Yousafzai", "Usain Bolt",
            "Rosa Parks", "Frida Kahlo", "Madre Teresa", "Harry Potter", "Wonder Woman", "Spider-Man", "Mario",
            "Princesa Zelda", "Maestro Jefe", "Geralt de Rivia", "Lionel Messi", "Beethoven", "Vincent van Gogh", "Muhammad Ali",
            "Charles Darwin", "Ada Lovelace", "Neil Armstrong", "Mozart", "Juana de Arco", "Emily Dickinson", "Vin Diesel",
            "Homero Simpson", "Pikachu", "Indiana Jones", "Neil deGrasse Tyson", "Hércules", "Mulan", "Goku", "Bilbo Bolsón",
            "Capitán Jack Sparrow", "Jules Verne", "Carmen Sandiego", "Princesa Leia", "Sherlock Holmes", "Hércules Poirot",
            "Elmo", "Rana Gustavo", "Napoleón", "Gandhi", "Pelé", "Juan Cena", "Darth Vader", "Cleopatra", "Albert Einstein",
            "Michael Jackson", "Leonardo da Vinci", "Serena Williams", "Martin Luther King Jr.", "Marilyn Monroe", "Winston Churchill",
            "Elon Musk", "Beyoncé", "Steve Jobs", "William Shakespeare", "Marie Curie", "Malala Yousafzai", "Usain Bolt",
            "Rosa Parks", "Frida Kahlo", "Madre Teresa", "Harry Potter", "Wonder Woman", "Spider-Man", "Mario",
            "Princesa Zelda", "Maestro Jefe", "Geralt de Rivia", "Lionel Messi", "Beethoven", "Vincent van Gogh", "Muhammad Ali",
            "Charles Darwin", "Ada Lovelace", "Neil Armstrong", "Mozart", "Juana de Arco", "Emily Dickinson", "Vin Diesel",
            "Homero Simpson", "Pikachu", "Indiana Jones", "Neil deGrasse Tyson", "Hércules", "Mulan", "Goku", "Bilbo Bolsón",
            "Capitán Jack Sparrow", "Jules Verne", "Carmen Sandiego", "Princesa Leia", "Sherlock Holmes", "Hércules",
            "Elmo", "Rana Gustavo"
        )

        return names[Random.nextInt(names.size)]
    }
}

class PlayerScoreAdapter(val playerList: MutableList<PlayerScoreData>) :
    RecyclerView.Adapter<PlayerScoreAdapter.PlayerScoreViewHolder>() {

    private var areScoresVisible = true

    fun toggleScoreVisibility() {
        areScoresVisible = !areScoresVisible
    }

    inner class PlayerScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerName)
        val nTextView: TextView = itemView.findViewById(R.id.textView3)
        val rTextView: TextView = itemView.findViewById(R.id.textView4)
        val jTextView: TextView = itemView.findViewById(R.id.textView5)
        val qTextView: TextView = itemView.findViewById(R.id.textView6)
        val kTextView: TextView = itemView.findViewById(R.id.textView7)
        val aTextView: TextView = itemView.findViewById(R.id.textView8)

        val nEditText: EditText = itemView.findViewById(R.id.editTextNumber)
        val rEditText: EditText = itemView.findViewById(R.id.editTextNumber2)
        val jEditText: EditText = itemView.findViewById(R.id.editTextNumber3)
        val qEditText: EditText = itemView.findViewById(R.id.editTextNumber4)
        val kEditText: EditText = itemView.findViewById(R.id.editTextNumber5)
        val aEditText: EditText = itemView.findViewById(R.id.editTextNumber6)

        val scoreTextView: TextView = itemView.findViewById(R.id.textView9)

        fun bind(player: PlayerScoreData) {


            val deleteButton: Button = itemView.findViewById(R.id.button2)
            deleteButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    playerList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }


            playerNameTextView.text = player.playerName
            nTextView.text = "N"
            rTextView.text = "R"
            jTextView.text = "J"
            qTextView.text = "Q"
            kTextView.text = "K"
            aTextView.text = "♠️"

            nEditText.setText(player.n.toString())
            rEditText.setText(player.r.toString())
            jEditText.setText(player.j.toString())
            qEditText.setText(player.q.toString())
            kEditText.setText(player.k.toString())
            aEditText.setText(player.a.toString())

            val score = calculateScore(player)
            scoreTextView.text = if (areScoresVisible) "Score: $score" else "Score: Hidden"

            nEditText.addTextChangedListener(createTextWatcher { text ->
                player.n = text.toIntOrNull() ?: 0
                updateScore(player)
            })
            rEditText.addTextChangedListener(createTextWatcher { text ->
                player.r = text.toIntOrNull() ?: 0
                updateScore(player)
            })
            jEditText.addTextChangedListener(createTextWatcher { text ->
                player.j = text.toIntOrNull() ?: 0
                updateScore(player)
            })
            qEditText.addTextChangedListener(createTextWatcher { text ->
                player.q = text.toIntOrNull() ?: 0
                updateScore(player)
            })
            kEditText.addTextChangedListener(createTextWatcher { text ->
                player.k = text.toIntOrNull() ?: 0
                updateScore(player)
            })
            aEditText.addTextChangedListener(createTextWatcher { text ->
                player.a = text.toIntOrNull() ?: 0
                updateScore(player)
            })
        }

        private fun createTextWatcher(afterTextChanged: (String) -> Unit): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    afterTextChanged(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }
        }

        private fun updateScore(player: PlayerScoreData) {
            val score = calculateScore(player)
            scoreTextView.text = if (areScoresVisible) "Score: $score" else "Score: Hidden"
        }
    }

    fun calculateScore(player: PlayerScoreData): Int {
        var score = 0
        score += player.n * 1
        score += player.r * 2
        score += player.j * 3
        score += player.q * 4
        score += player.k * 5
        score += player.a * 6
        return score
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player, parent, false)
        return PlayerScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerScoreViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

}
