package jscheme

import org.junit.Test

class InputPortTest {

    private InputPort port

    private parse(String string) {
        new InputPort(new StringReader(string)).read()
    }

    // --------------------------------------------------------------
    // Tokens
    // --------------------------------------------------------------

    @Test
    void parseTrue() {
        assert parse('#t') == true
        assert parse('#T') == true
    }

    @Test
    void parseFalse() {
        assert parse('#f') == false
        assert parse('#F') == false
    }

    @Test
    void parseInteger() {
        assert parse('42') instanceof Double
    }

    @Test
    void parseDecimal() {
        assert parse('3.14') instanceof Double
    }

    @Test
    void parseSymbol() {
        def symbol = parse(/'symbol/)
        assert symbol instanceof Pair
        assert symbol.first == 'quote'
        assert symbol.rest.first == 'symbol'
    }

    @Test
    void parseString() {
        assert parse('"Lorem Ipsum"') == 'Lorem Ipsum' as char[]
    }

    @Test
    void parseComment() {
        assert parse('5 ; comment') == 5
    }
}
