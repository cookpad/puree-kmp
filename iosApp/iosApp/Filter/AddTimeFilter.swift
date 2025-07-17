import Foundation
import PureeKMP

class AddTimeFilter: PureeFilter {
    func applyFilter(log: String) -> String? {
        guard let data = log.data(using: .utf8),
              var jsonObject = try? JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] else {
            return log
        }

        let dateFormatter = ISO8601DateFormatter()
        dateFormatter.formatOptions = [.withInternetDateTime, .withFractionalSeconds]

        let currentTimeString = dateFormatter.string(from: Date())
        jsonObject["time"] = currentTimeString

        guard let newData = try? JSONSerialization.data(withJSONObject: jsonObject, options: []),
              let newJsonString = String(data: newData, encoding: .utf8) else {
            return log
        }

        return newJsonString
    }
}
