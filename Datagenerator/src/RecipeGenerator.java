import java.io.File;
import java.io.FileWriter;

public class RecipeGenerator {
	public static void generate(String[] shape, String[] items, String name, String modName, String output, String group, int count) {
		String path2 = DataGenerator.gameDir + "\\src\\main\\resources\\data\\" + modName + "\\";
		
		String recipe = "{\n" +
				"  \"type\": \"minecraft:crafting_shaped\",\n" +
				"  \"group\": \"%group%\",\n" +
				"  \"pattern\": [\n";
		int i = 0;
		for (; i < shape.length - 1; i++) {
			recipe += "    \"" + shape[i] + "\",\n";
		}
		recipe += "    \"" + shape[i] + "\"\n";
		recipe +=
				"  ],\n" +
						"  \"key\": {\n";
		for (i = 0; i < items.length - 1; i++) {
			recipe += "    " + items[i] + ",\n";
		}
		recipe += "    " + items[i] + "\n";
		recipe += "  },\n" +
				"  \"result\": {\n" +
				"    \"item\": \"%output%\",\n" +
				"    \"count\": " + count + "\n" +
				"  }\n" +
				"}";
		
		File loottable = new File(path2 + "recipes\\" + name + ".json");
		try {
			if (!loottable.exists()) {
				loottable.createNewFile();
			}
			FileWriter writer = new FileWriter(loottable);
			writer.write(recipe.replace("%group%", group).replace("%output%", output)/*.replace("\n","").replace(" ","")*/);
			writer.close();
		} catch (Throwable ignored) {
		}
	}
}
