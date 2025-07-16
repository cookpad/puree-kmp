import SwiftUI
import Combine
import PureeKMP

struct ContentView: View {
    @State private var periodicLogSequence = 0
    @State private var isSendLogPerSecond = false

    let log = Log()
    let timer = Timer.publish(every: 1.0, on: .main, in: .common).autoconnect()
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Button(action: {
                    log.send(ClickLog(buttonName: "button"))
                }) {
                    Text("Send log")
                        .font(.headline)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                
                Button(action: {
                    log.flush()
                }) {
                    Text("Flush!")
                        .font(.headline)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                
                HStack(spacing: 16) {
                    Text("Log every second")
                        .font(.subheadline)
                    Toggle("", isOn: $isSendLogPerSecond)
                        .labelsHidden()
                }
                
                Spacer()
            }
            .padding()
            .navigationBarTitle("puree-kmp", displayMode: .inline)
            .navigationBarItems(trailing: Button(action: {
                log.send(MenuLog(menuName: "add"))
            }) {
                Image(systemName: "plus")
            })
        }
        // 1秒ごとにトグルがオンなら PeriodicLog を送信
        .onReceive(timer) { _ in
            if isSendLogPerSecond {
                periodicLogSequence += 1
                log.send(PeriodicLog(sequence: periodicLogSequence))
            }
        }
    }
}

#Preview {
    ContentView()
}
