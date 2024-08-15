
import org.scibot.*
import java.util.logging.Level

class PluginMain : Interfaces.Plugin {
    var sender: Interfaces.SimpleSender? = null
    var logger: Interfaces.SimpleLogger? = null
    /*
    This is the sample plugin of SciBot.
    Define Main class and Plugin Name in resources/plugin.yml
    use @GroupHandler to create an event handler.
    implements Plugin interface to create a main class.
     */
    override suspend fun start() {
        if(logger != null){
            throw IllegalArgumentException("logger isn't given")
        }
        if(sender != null){
            throw IllegalArgumentException("sender isn't given")
        }
        logger!!.log("hello,World", Level.INFO)
        sender!!.plainSend("hello,world", Sender.Type.GROUP,946085440)
        sender!!.send(mutableListOf(Events.AtMessage(1294915648L)), Sender.Type.GROUP, 946085440)
    }
    @Annonations.GroupHandler(MessageConstructor.Types.PLAIN)
    fun doSomething(event: Events.MajorEvent) {
        println("MyPlugin called")
        event.msgArr.forEach { any ->
            if (any is Events.PlainMessage) {
                logger?.log("Received message: ${any.message} from ${event.sender.uid}")
            }
        }
    }
    fun getLogger(logger: Interfaces.SimpleLogger){
        this.logger = logger
    }
    fun getSender(sender: Interfaces.SimpleSender){
        this.sender = sender
    }
}