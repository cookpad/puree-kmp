import SwiftUI
import Combine
import Puree

struct ContentView: View {
    @State private var periodicLogSequence = 0
    @State private var isSendLogPerSecond = false

    let puree = Puree()
    let timer = Timer.publish(every: 1.0, on: .main, in: .common).autoconnect()
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Button(action: {
                    puree.send(ClickLog(buttonName: "button"))
                }) {
                    Text("Send log")
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
                puree.send(MenuLog(menuName: "add"))
            }) {
                Image(systemName: "plus")
            })
        }
        // 1秒ごとにトグルがオンなら PeriodicLog を送信
        .onReceive(timer) { _ in
            if isSendLogPerSecond {
                periodicLogSequence += 1
                puree.send(PeriodicLog(sequence: periodicLogSequence))
            }
        }
    }
}

#Preview {
    ContentView()
}
