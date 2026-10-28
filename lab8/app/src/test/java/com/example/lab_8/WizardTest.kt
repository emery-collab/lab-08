package com.example.lab_8


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WizardTest {
    private lateinit var evilWizard: Wizard

    // Set up a new wizard before every single test
    @Before
    fun setUp() {
        evilWizard = Wizard("Evil Wizard", 30, 10)
    }

    @Test
    fun castSpell_explosion_successful() {
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(30, damageDealt) // spellPower of 10 * 3 = 30

        assertEquals(20, evilWizard.mana) // mana of 30 - 10 = 20
    }

    @Test
    fun castSpell_explosion_unsuccessful() {
        evilWizard.mana = 5 // Evil Wizard does not have enough mana to cast "Explosion"
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(0, damageDealt)

        assertEquals(5, evilWizard.mana) // mana still is 5
    }

    @Test
    fun restoreMana_amountAddedExceedsOneHundred_manaSetToOneHundred() {
        evilWizard.restoreMana(80) // 30 + 80 = 110, so mana should be set to 100

        assertEquals(100, evilWizard.mana)
    }

    @Test
    fun restoreMana_amountAddedDoesNotExceedOneHundred_manaIncreasesCorrectly() {
        evilWizard.restoreMana(40) // 30 + 40 = 70, so mana should be set to 70

        assertEquals(70, evilWizard.mana)
    }

    @Test
    fun train_sufficientManaForTraining_spellPowerIncreasesCorrectly() {
        // first set the values for mana and spellPower so we can test correctly
        evilWizard.mana = 5
        evilWizard.spellPower = 1

        evilWizard.train() // mana greater than 5, so spellPower should increase by 1, mana goes down by 5

        assertEquals(0, evilWizard.mana)
        assertEquals(2, evilWizard.spellPower)
    }

    @Test
    fun train_insufficientManaForTraining_spellPowerUnaffected() {
        // again, set the mana and spellPower first to guarantee the results
        evilWizard.mana = 4
        evilWizard.spellPower = 1

        evilWizard.train() // mana less than 5, so spellPower and mana shouldn't change

        assertEquals(4, evilWizard.mana)
        assertEquals(1, evilWizard.spellPower)
    }

}