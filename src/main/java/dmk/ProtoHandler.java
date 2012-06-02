
// line 1 "ProtocolHandler.ragel"
package dmk;

import java.io.BufferedOutputStream;

public class ProtoHandler {
	protected int firstNum;
	protected int secondNum;
	

// line 13 "ProtoHandler.java"
private static byte[] init__protohandler_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3
	};
}

private static final byte _protohandler_actions[] = init__protohandler_actions_0();


private static byte[] init__protohandler_key_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4,    5,    6,    7,    8,    9,   11,   13,   15,
	   17,   18,   20
	};
}

private static final byte _protohandler_key_offsets[] = init__protohandler_key_offsets_0();


private static byte[] init__protohandler_trans_keys_0()
{
	return new byte [] {
	    1,    2,    0,    9,  104,  101,  108,  108,  111,    1,    2,    0,
	    9,    1,    2,    0,    9,    0,    0,    9,    0,    2,    3,    4,
	    0
	};
}

private static final byte _protohandler_trans_keys[] = init__protohandler_trans_keys_0();


private static byte[] init__protohandler_single_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    1,    1,    1,    1,    1,    0,    0,    0,    0,
	    1,    0,    4
	};
}

private static final byte _protohandler_single_lengths[] = init__protohandler_single_lengths_0();


private static byte[] init__protohandler_range_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    0,    0,    0,    0,    0,    1,    1,    1,    1,
	    0,    1,    0
	};
}

private static final byte _protohandler_range_lengths[] = init__protohandler_range_lengths_0();


private static byte[] init__protohandler_index_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4,    6,    8,   10,   12,   14,   16,   18,   20,
	   22,   24,   26
	};
}

private static final byte _protohandler_index_offsets[] = init__protohandler_index_offsets_0();


private static byte[] init__protohandler_trans_targs_0()
{
	return new byte [] {
	    2,    0,    3,    0,    4,    0,    5,    0,    6,    0,    7,    0,
	   14,    0,    9,    0,   14,    0,   11,    0,   14,    0,   13,    0,
	   14,    0,    1,    8,   10,   12,    0,    0
	};
}

private static final byte _protohandler_trans_targs[] = init__protohandler_trans_targs_0();


private static byte[] init__protohandler_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,    0,    0,    0,    3,    0,    0,    0,    5,    0,    0,    0,
	    7,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final byte _protohandler_trans_actions[] = init__protohandler_trans_actions_0();


static final int protohandler_start = 14;
static final int protohandler_first_final = 14;
static final int protohandler_error = 0;

static final int protohandler_en_main = 14;


// line 13 "ProtocolHandler.ragel"


	public void stateMachine(BufferedOutputStream bos, byte[] data, int len, final String programVersion, final String hostAndPort) 
	throws java.io.UnsupportedEncodingException, java.io.IOException{
			int cs = 0;
			int p = 0;
			int pe = data.length;
			if (len > 0) {

				
// line 122 "ProtoHandler.java"
	{
	cs = protohandler_start;
	}

// line 127 "ProtoHandler.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _protohandler_key_offsets[cs];
	_trans = _protohandler_index_offsets[cs];
	_klen = _protohandler_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _protohandler_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _protohandler_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _protohandler_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _protohandler_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _protohandler_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _protohandler_trans_targs[_trans];

	if ( _protohandler_trans_actions[_trans] != 0 ) {
		_acts = _protohandler_trans_actions[_trans];
		_nacts = (int) _protohandler_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _protohandler_actions[_acts++] )
			{
	case 0:
// line 25 "ProtocolHandler.ragel"
	{
						System.out.println(Thread.currentThread().getName() + " ragel in state 0. Found an initiation - 0 hello");
						System.out.println(Thread.currentThread().getName()  + " len =" + len);
						ProtoHandler.dump(data, len);
						byte state = data[0];
						byte type = data[1];
						byte valueLen = data[2];
						byte[] arr = new byte[valueLen];
						System.arraycopy(data, 3, arr, 0, valueLen);
						ProtoHandler.dump(arr, valueLen);
						String s = new String(arr);
						System.out.println(Thread.currentThread().getName() + " " + state + " " + type + " " + valueLen + " value=" + s);
						String response = programVersion + " running on " + hostAndPort;
						bos.write(response.getBytes("ASCII"));
						bos.flush();						
					}
	break;
	case 1:
// line 41 "ProtocolHandler.ragel"
	{ 
						System.out.println(Thread.currentThread().getName() + " ragel in state 2. Found a request to receive 1st num");
						ProtoHandler.dump(data, len);
						byte state = data[0];
						byte type = data[1];
						byte valueLen = data[2];
						byte[] arr = new byte[valueLen];
						System.arraycopy(data, 3, arr, 0, valueLen);
						ProtoHandler.dump(arr, valueLen);
						firstNum = arr[0];
						System.out.println(Thread.currentThread().getName() + " " + state + " " + type + " " + valueLen + " firstNum=" + firstNum);
					}
	break;
	case 2:
// line 53 "ProtocolHandler.ragel"
	{ 
						System.out.println(Thread.currentThread().getName() + " ragel in state 3. Found a request to receive 2nd num");
						ProtoHandler.dump(data, len);
						byte state = data[0];
						byte type = data[1];
						byte valueLen = data[2];
						byte[] arr = new byte[valueLen];
						System.arraycopy(data, 3, arr, 0, valueLen);
						ProtoHandler.dump(arr, valueLen);
						secondNum = arr[0];
						System.out.println(Thread.currentThread().getName() + " " + state + " " + type + " " + valueLen + " secondNum=" + secondNum);
					}
	break;
	case 3:
// line 65 "ProtocolHandler.ragel"
	{ 
						System.out.println(Thread.currentThread().getName() + " ragel in state 4. Found a request to return the addition of the numbers.");
						System.out.println(Thread.currentThread().getName() + " returning " + (firstNum + secondNum));
						Integer sum = firstNum + secondNum;
						bos.write(sum.byteValue());
						bos.flush();
					}
	break;
// line 265 "ProtoHandler.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
case 5:
	}
	break; }
	}

// line 75 "ProtocolHandler.ragel"

				//send back ip address and port
			}
	}

//main := (0 'hello') @ { 					//works
/**
					
				%%{
					main := ((0 1..2 digit+ 'hello') @ { 					
						System.out.println(Thread.currentThread().getName() + " ragel found a 0");
						System.out.println(Thread.currentThread().getName() + "len =" + len);
						ProtoHandler.dump(data, len);
						byte[] arr = new byte[len];
						byte state = data[0];
						byte type = data[1];
						byte valueLen = data[2];
						System.arraycopy(data, 3, arr, 0, len);
						ProtoHandler.dump(arr, len);
						String s = new String(arr);
						System.out.println(Thread.currentThread().getName() +  " " + state + " " + type + " " + valueLen + " value=" + s);
						
					}
						  | 1 @ { System.out.println("ragel found a 1"); }					
						  | 2 @ { System.out.println("ragel found a 2"); }
						  | 3 @ { System.out.println("ragel found a 3"); }
						  | 4 @ { System.out.println("ragel found a 4"); }
					)*;
					write init;
					write exec;					
				}%%


**/
	protected static void dump(final byte[] buf, int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(buf[i] + " ");
		}
		System.out.println();
	}
	
	protected static void dumpAsHex(final byte[] buf, int len) {
		System.out.println(ProtoHandler.getHex(buf, len));
		System.out.println();
	}

	private static final String HEXES = "0123456789ABCDEF";

	public static String getHex(byte[] raw, int len) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		byte b;
		for (int i = 0; i < len; i++) {
			b = raw[i];
//			System.out.println("b = " + b + " " + (b & 0xF0) + " "
//				+ ((b & 0xF0) >> 4) + " " + (b & 0x0F));
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
}
