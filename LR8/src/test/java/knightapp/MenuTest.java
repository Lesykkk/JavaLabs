package knightapp;

import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class MenuTest {
    private Menu menu;
    private Scanner mockScanner;
    private Map<Integer, Command> mockCommands;

    @BeforeEach
    void setUp() {
        menu = new Menu() {};
        mockScanner = mock(Scanner.class);
        mockCommands = new HashMap<>();

        // Mock each command with a simple description and behavior
        for (int i = 1; i <= 8; i++) {
            Command mockCommand = mock(Command.class);
            when(mockCommand.getDesc()).thenReturn("Mock Command " + i);
            mockCommands.put(i, mockCommand);
        }

        menu.menuCommands = mockCommands;
        menu.scanner = mockScanner;
    }

    @Test
    void testDisplayExitOption() {
        when(mockScanner.nextInt()).thenReturn(0);
        assertDoesNotThrow(() -> menu.display());
    }

    @Test
    void testDisplayValidCommandExecution() {
        when(mockScanner.nextInt()).thenReturn(1, 0);
        assertDoesNotThrow(() -> menu.display());
        verify(mockCommands.get(1), times(1)).execute();
    }

    @Test
    void testDisplayInvalidCommand() {
        when(mockScanner.nextInt()).thenReturn(99, 0);
        assertDoesNotThrow(() -> menu.display());
        mockCommands.values().forEach(command -> verify(command, never()).execute());
    }
}
