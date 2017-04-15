package swotsuite.framework;

import java.io.Serializable;
import java.util.List;

public class m3Struct implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> suggestionInfo;

	public List<String> getsuggestionInfo() {
		return suggestionInfo;
	}

	private String deduceInfo;

	public String getdeduceInfo() {
		return deduceInfo;
	}

	public m3Struct(List<String> suggestionInfo, String deduceInfo) {

		this.deduceInfo = deduceInfo;
		this.suggestionInfo = suggestionInfo;
	}
}
