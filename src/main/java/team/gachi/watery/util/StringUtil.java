package team.gachi.watery.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringUtil extends org.apache.commons.lang3.StringUtils {
    public static List<String> parseToList(String input) {
        if (input == null || input.length() < 2) {
            return Collections.emptyList();
        }

        // 양 끝 대괄호 제거
        String trimmed = input.substring(1, input.length() - 1).trim();

        if (trimmed.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(trimmed.split(","))
                .map(s -> s.trim().replaceAll("^\"|\"$", "")) // 앞뒤 따옴표 제거
                .collect(Collectors.toList());
    }
}
