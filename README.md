# Flow API Showcase

An Android app that serves as a personal research for exploring the capabilities of the Kotlin's Flow API.  

## 🚧 [IN PROGRESS]
At the time present, the app only offers a simple use of SharedFlow and StateFlow. More examples based on other features will be added in the future.

## Bus classes
 * BroadcastEventBus<T> -> Wraps a MutableSharedFlow<T> and publishes a read-only getter and a method to emit new values. 
 * BroadcastStateBus<T> -> Wraps a MutableStateFlow<T> and publishes a read-only getter and a method to update the value. 

## Dialer
The DialerActivity is composed of three DialerCollectorFragment instances and a single DiallerEmitterFragment. The emitter fragment has a numerical pad, which sends each number clicked through the provided SharedFlow, and a button, which counts how many clicks has been performed in that button, and sends each new count through the provided StateFlow. The three collector fragments will receive updates on these two flows as long as their respective switches are enabled.
The SharedFlow is configured to reply 3 elements (this means, any time a fragment starts collecting from the flow, it will receive the latest 3 values emitted).  
