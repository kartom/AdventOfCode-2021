val rules = mutableMapOf<String, Rule>()
const val maxSteps = 40

class Rule(str:String, private val c : Char) {
    private val cnt = Array<Map<Char,Long>?>(maxSteps) { null }
    private val c1=str[0].toString()
    private val c2=str[1].toString()
    init { cnt[0] = mapOf(c to 1.toLong()) }

    fun getCnt(step: Int): Map<Char,Long> {
        val index = step-1
        if( cnt[index]==null ) {
            val cnt1 = rules[c1 + c]?.getCnt(step - 1) ?: mutableMapOf<Char, Long>()
            val cnt2 = rules[c + c2]?.getCnt(step - 1) ?: mutableMapOf<Char, Long>()
            cnt[index]=(cnt1.keys+cnt2.keys+c).associateWith {
                cnt1.getOrDefault(it,0)+ cnt2.getOrDefault(it,0)+(if(it==c) 1 else 0) }
        }
        return cnt[index]!!
    }
}

fun doSteps(template: String, steps: Int): Long {
    val cnt = template.toList().toSet().associateWith { c ->
        template.count { it == c }.toLong() }.toMap().toMutableMap()
    (0 until template.length - 1).forEach { i ->
        val str = template.substring(i, i + 2)
        rules[str]!!.getCnt(steps).forEach { cnt[it.key] = cnt.getOrDefault(it.key, 0) + it.value }
    }
    return cnt.maxOf { it.value }-cnt.minOf { it.value }
}

fun main()  {
    val lines = java.io.File("data/Day-14-data.txt").readLines()
    val template = lines.first()
    lines.takeLastWhile { it.isNotBlank() }.forEach { line ->
        line.split(' ').also { rules[it[0]]=Rule(it[0],it[2][0]) } }

    println("Day 14, part 1: ${doSteps(template,10)}")
    println("Day 14, part 2: ${doSteps(template,40)}")
}