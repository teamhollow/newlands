import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Tag {
	private final StringBuilder text = new StringBuilder("{\n  \"replace\": false,\n  \"values\": [\n");
	
	public void append(String block) {
		text.append("    \"").append(block).append("\",\n");
	}
	
	public void append(String block, boolean isLast) {
		text.append("    \"").append(block).append(isLast ? "\"" : "\",").append("\n");
	}
	
	public void populate(List<String> blocks) {
		for (int i = 0; i < blocks.size(); i++) {
			if (i == blocks.size() - 1) {
				append(blocks.get(i), true);
			} else {
				append(blocks.get(i));
			}
		}
	}
	
	private final String path1 = DataGenerator.gameDir + "\\src\\main\\resources\\data\\%modid%";
	
	public void write(String filepath) {
		File file = new File(path1.replace("%modid%","minecraft") + filepath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(getString());
			writer.close();
		} catch (Throwable ignored) {
		}
	}
	
	public void write(String modID, String filepath) {
		File file = new File(path1.replace("%modid%",modID) + filepath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(getString());
			writer.close();
		} catch (Throwable ignored) {
		}
	}
	
	public String getString() {
		return text.toString() + "  ]\n}";
	}
}
