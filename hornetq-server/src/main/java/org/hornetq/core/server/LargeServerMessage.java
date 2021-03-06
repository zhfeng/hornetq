/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.core.server;

import org.hornetq.api.core.HornetQException;
import org.hornetq.core.journal.SequentialFile;
import org.hornetq.core.replication.ReplicatedLargeMessage;


/**
 * A LargeMessage
 *
 * @author <a href="mailto:clebert.suconic@jboss.org">Clebert Suconic</a>
 */
public interface LargeServerMessage extends ServerMessage, ReplicatedLargeMessage
{
   void addBytes(byte[] bytes) throws Exception;

   void setPendingRecordID(long pendingRecordID);

   long getPendingRecordID();

   /**
    * We have to copy the large message content in case of DLQ and paged messages
    * For that we need to pre-mark the LargeMessage with a flag when it is paged
    */
   void setPaged();

   /** Close the files if opened */
   void releaseResources();

   void deleteFile() throws Exception;

   void incrementDelayDeletionCount();

   void decrementDelayDeletionCount() throws Exception;

   /**
    * @return
    * @throws HornetQException
    */
   SequentialFile getFile() throws HornetQException;
}
