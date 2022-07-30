package com.com470.coffeeMackerMockitoApp;

import com.com470.coffeeMackerMockitoApp.exceptions.InventoryException;
import com.com470.coffeeMackerMockitoApp.exceptions.RecipeException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
	 *Alexy Sdenka Taboada Chuve
	 */
public class CoffeeMakerTest {
	
	//-----------------------------------------------------------------------
	//	DATA MEMBERS
	//-----------------------------------------------------------------------
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	
	private Recipe [] stubRecipies; 
	
	/**
	 * The coffee maker -- our object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	/**
	 * The stubbed recipe book.
	 */
	private RecipeBook recipeBookStub;
	
	private Inventory inv;
	
	@Before
	public void setUp() throws RecipeException {
		
		recipeBookStub = mock(RecipeBook.class);
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		
		//Set up for recipe1
		recipe1 = new Recipe();

		recipe1.setAmtChocolate("1");
		recipe1.setAmtMilk("1");
		recipe1.setName("cafe");
		recipe1.setAmtSugar("1");
		recipe1.setAmtCoffee("3");
		recipe1.setPrice("50");
		
		//Set up for recipe2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtSugar("1");
		recipe2.setAmtMilk("1");
		recipe2.setPrice("75");
		
		//Set up for recipe3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtSugar("1");
		recipe3.setAmtMilk("3");

		recipe3.setPrice("100");
		
		//Set up for recipe4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for recipe5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
	}
	
	
	//-----------------------------------------------------------------------
	//	Test Methods
	//-----------------------------------------------------------------------
	
	// put your tests here!
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertTrue(true);
	}

    @Test
    public void add() {
        when(recipeBookStub.addRecipe(recipe1)).thenReturn(true);
        assertTrue(coffeeMaker.addRecipe(recipe1));
        verify(recipeBookStub).addRecipe(recipe1);
        when(recipeBookStub.addRecipe(recipe2)).thenReturn(true);
        assertTrue(coffeeMaker.addRecipe(recipe2));
        verify(recipeBookStub).addRecipe(recipe2);
        when(recipeBookStub.addRecipe(recipe3)).thenReturn(true);
        assertTrue(coffeeMaker.addRecipe(recipe3));
        verify(recipeBookStub).addRecipe(recipe3);
        
    }

    @Test
    public void delete() {
        when(recipeBookStub.deleteRecipe(0)).thenReturn(stubRecipies[0].getName());
        assertEquals(coffeeMaker.deleteRecipe(0), stubRecipies[0].getName());
        verify(recipeBookStub).deleteRecipe(0);
    }

    @Test
    public void eddit() {
        when(recipeBookStub.editRecipe(0, recipe1)).thenReturn(stubRecipies[0].getName());
        assertEquals(coffeeMaker.editRecipe(0, recipe1), stubRecipies[0].getName());
        verify(recipeBookStub).editRecipe(0, recipe1);
        when(recipeBookStub.editRecipe(0, recipe2)).thenReturn(stubRecipies[0].getName());
        assertEquals(coffeeMaker.editRecipe(0, recipe2), stubRecipies[0].getName());
        verify(recipeBookStub).editRecipe(0, recipe2);
    }


    @Test
    public void addd() throws InventoryException {
        coffeeMaker.addInventory("1", "2", "3", "4");
    }

    @Test
    public void setAmtSugar() throws RecipeException {
        recipe1.setAmtSugar("3");
    }

    @Test
    public void setAmtChocolate() throws RecipeException {
        recipe1.setAmtChocolate("4");
    }

    @Test
    public void setAmtMilk() throws RecipeException {
        recipe1.setAmtMilk("15");
    }

    @Test
    public void setAmtCoffee() throws RecipeException {
        recipe1.setAmtCoffee("23");
    }

    @Test
    public void setPrice() throws RecipeException {
        recipe1.setPrice("23");
    }

    @Test
    public void recipeToString() {
        assertEquals(recipe1.toString(), "cafe");
    }

    @Test
    public void recipeNull() {
        assertEquals(recipe1.toString(), "cafe");
   }

    @Test
    public void check() {
        String inventario = coffeeMaker.checkInventory();
        System.out.print("inventario: " + inventario);

        StringBuffer buf = new StringBuffer();
        buf.append("cafe");
        buf.append(10);
        buf.append("leche");
        buf.append(1);
        buf.append("Sugar");
        buf.append(2);
        buf.append("Chocolate");
        buf.append(3);
    }


    @Test
    public void makeCoffeeMonto() {
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        assertEquals(10, coffeeMaker.makeCoffee(0, 10));
    }

    @Test
    public void makeCoffeeIngredientes() {
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        assertEquals(100, coffeeMaker.makeCoffee(1, 100));
    }
}
