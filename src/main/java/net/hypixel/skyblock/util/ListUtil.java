package net.hypixel.skyblock.util;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

/**
 * Utilities for {@link List}.<br>
 *
 * @author MrPineapple070
 * @version 12 October 2020
 * @since 12 October 2020
 */
public class ListUtil {
	/**
	 * Reads {@link Ingredient} from a {@link JsonArray}
	 *
	 * @param json_array {@link JsonArray} to read from
	 * @return {@link NonNullList} of {@link Ingredient} read.
	 */
	public static NonNullList<Ingredient> readIngredients(JsonArray json_array) {
		final NonNullList<Ingredient> ingredients = NonNullList.create();
		for (final JsonElement json : json_array) {
			final Ingredient ingredient = Ingredient.deserialize(json);
			if (!ingredient.hasNoMatchingItems())
				ingredients.add(ingredient);
		}
		return ingredients;
	}

	/**
	 * Remove all instances from a list of elements from a primative type array.
	 *
	 * @param <E>      type held in the array that extends {@link Object}
	 * @param list     primative type array to edit.
	 * @param elements elements to remove
	 * @return elements removed from list
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Object> E[] removeAll(E[] list, E... elements) {
		final List<E> temp = Arrays.asList(list);
		temp.removeAll(Arrays.asList(elements));
		return (E[]) temp.toArray();
	}

	/**
	 * Remove {@code null} from a primative type array.
	 *
	 * @param <E>  type held in the array that extends {@link Object}
	 * @param list primative type array to edit.
	 * @return null removed from list
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Object> E[] removeNull(E[] list) {
		return (E[]) Arrays.stream(list).filter(x -> x != null).toArray();
	}

	/**
	 * Sets all elements in a {@link List} to element
	 *
	 * @param <E>     type held in the list that extends {@link Object}
	 * @param element the element to set
	 * @param list    {@link List} to set
	 * @return converted {@link List}
	 */
	public static <E extends Object> List<E> setAll(@Nullable E element, @Nonnull List<E> list) {
		for (int i = 0; i < list.size(); i++)
			list.set(i, element);
		return list;
	}
}