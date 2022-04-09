/************************************************
 *
 * Author: Bryce McWhirter
 * Assignment: Program 4
 * Class: Data Communications
 *
 ************************************************/

package notifi.serialization;


import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 * The NoTiFi Ack is a type of NoTiFi Message
 * that represents an acknowledgement of a received
 * notification.
 */
public class NoTiFiACK extends NoTiFiMessage{


    /**
     * The Operation Code Specifying an ACK
     */
    public static final byte ACK_CODE = 0x03;
    private static final int ACK_SIZE = 2;


    /**
     * Constructs a NoTiFi Ack Message
     * @param msgId the message id
     * @throws IllegalArgumentException
     *      if invalid message id
     */
    public NoTiFiACK(int msgId) throws IllegalArgumentException{
        super(msgId);
    }


    /**
     * Serializes a NoTiFiAck
     * @return a serialized NoTiFiAck
     */
    @Override
    public byte[] encode() {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteStream);

        try {
            writeNoTiFiHeader(out, ACK_CODE);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = byteStream.toByteArray();

        if(data.length != ACK_SIZE){
            throw new IllegalArgumentException("Ack is not properly sized");
        }


        return data;

    }

    @Override
    public int getCode() {
        return ACK_CODE;
    }


    /** Returns a string representation of an ACK message
     * @return the string representation of an ACK
     */
    @Override
    public String toString() {
        return "ACK: msgid="+msgId;
    }



}
