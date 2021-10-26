import React, { useCallback } from "react";
import { cn } from '@bem-react/classname';
import { IClassNameProps } from '@bem-react/core';
import { Container } from '../../Container';

import './Chat.scss';
import { Message } from "./Message/Message";

interface IChatProps extends IClassNameProps {
    dialog: any,
    preview?: boolean,
}

const cnChat = cn('Chat');

export const Chat: React.FC<IChatProps> = (props) => {
    const { dialog, preview = false } = props;

    const renderChat = useCallback(() => {
        console.log('here')
        console.log(Object.keys(dialog));

        // const zeroExists = dialog['I0'] || dialog['A0'];

        const firstTurn = Boolean(dialog['I0']);
        // const firstPhrase = dialog['I0'] ? dialog['I0'] : dialog['A0'];

        const getPosition = (whos: Boolean) => whos ? 'left' : 'right';

        // const firstMessage = (
        //     <Message position={getPosition(firstTurn)} >
        //         {firstPhrase}
        //     </Message>
        // )

        const length = preview ? 1 : Math.ceil((Object.keys(dialog).length) / 2);
    
        const currentTurn = firstTurn;
        // const currentTurn = !firstTurn;
        const messages: any = [];

        for(let i = 1; i <= length; i++) {
            let first = (
                <Message position={getPosition(currentTurn)}>
                    {firstTurn ? dialog[`A${i}`] : dialog[`I${i}`]}
                </Message>
            )

            let second = (
                <Message position={getPosition(!currentTurn)}>
                    {firstTurn ? dialog[`I${i}`] : dialog[`A${i}`]}
                </Message>
            )
            messages.push(first);
            messages.push(second);
        }

        console.log(messages.length)

        return (
            <>
                {/* {firstMessage} */}
                {Object.values(messages)}
            </>
        )
    }, [dialog])

    return (
        <Container flexDirection="column">
            {renderChat()}
        </Container>
    )
}