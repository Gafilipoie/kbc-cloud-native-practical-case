package com.ezgroceries.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;

@SpringBootApplication
@EnableFeignClients
public class EzGroceriesShoppingListApplication {
	public static void main(String[] args) {
		SpringApplication.run(EzGroceriesShoppingListApplication.class, args);
	}

	public static class ShoppingListDTO {
		String id;
		String name;
		ArrayList<String> ingredients = new ArrayList<>();

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<String> getIngredients() {
			return this.ingredients;
		}

		public void setIngredients(ArrayList<String> ingredients) {
			for (String ingredient : ingredients) {
				if (this.ingredients.contains(ingredient)) continue;
				this.ingredients.add(ingredient);
			}
		}
	}
}
